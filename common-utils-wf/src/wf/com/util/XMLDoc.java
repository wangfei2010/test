package wf.com.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 内存 XML 管理对象
 * @author WangFei
 */
public class XMLDoc {
	private Document xmlDoc;

	/**
	 * 从文件加载为内存XML对象
	 * @param path
	 * 文件路径
	 * @throws Exception
	 * 初始化失败
	 */
	public void loadFromXMLFile(String xmlFile) throws Exception {
		try {
			FileInputStream stream = new FileInputStream(xmlFile);
			this.load(stream);
		}
		catch (Exception e) {
			throw new Exception("加载XML文件失败");
		}
	}

	/**
	 * 从 XML 字符串加载为内存对象
	 * @param xml
	 * XML字符串
	 * @throws Exception
	 * 初始化失败
	 */
	public void loadFromXMLString(String xmlString) throws Exception {
		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(xmlString.getBytes("utf-8"));
			this.load(stream);
		}
		catch (Exception e) {
			throw new Exception("加载XML字符串失败");
		}
	}

	public void load(InputStream stream) throws Exception {
		if (stream == null) {
			return;
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		xmlDoc = builder.parse(stream);
		xmlDoc.normalize();

		stream.close();
	}

	/**
	 * 创建根节点
	 * @param rootName
	 * 根节点名称
	 * @return 根节点对象
	 */
	public Element initRootElement(String rootName) {
		if (StringUtils.isEmpty(rootName)) {
			return null;
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e) {
			return null;
		}

		xmlDoc = builder.newDocument();
		Element e = xmlDoc.createElement(rootName);

		xmlDoc.appendChild(e);
		return e;
	}

	/**
	 * 获取根节点
	 * @return
	 */
	public Element getRootElement() {
		if (xmlDoc == null) {
			return null;
		}

		return xmlDoc.getDocumentElement();
	}

	/**
	 * 获取子节点集合
	 * @param e
	 * @return
	 */
	public List<Element> getChildList(Element elem) {
		List<Element> childs = new ArrayList<Element>();
		if ((xmlDoc == null) || (elem == null)) {
			return null;
		}

		NodeList list = elem.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			if (list.item(i) instanceof Element) {
				childs.add((Element) list.item(i));
			}
		}
		return childs;
	}

	/**
	 * 根据父节点创建子节点
	 * @param parentNode
	 * 父节点
	 * @param childName
	 * 子节点名称
	 * @param childValue
	 * 子节点值
	 * @return
	 */
	public Element appendChildNode(Element parentNode, String childName, String childValue) {
		if ((xmlDoc == null) || (parentNode == null)) {
			return null;
		}
		if (StringUtils.isEmpty(childName)) {
			return null;
		}

		Element child = xmlDoc.createElement(childName);
		if (child == null) {
			return null;
		}

		child.setTextContent(childValue);
		// 添加子节点
		parentNode.appendChild(child);

		return child;
	}

	/**
	 * 根据父节点创建子节点
	 * @param parentNode
	 * 父节点
	 * @param childName
	 * 子节点名称
	 * @param childValue
	 * 子节点值
	 * @return
	 */
	public Comment appendComment(Element parentNode, String commentText) {
		if ((xmlDoc == null) || (parentNode == null)) {
			return null;
		}
		if (StringUtils.isEmpty(commentText)) {
			return null;
		}

		Comment child = xmlDoc.createComment(commentText);
		if (child == null) {
			return null;
		}

		// 添加子节点
		parentNode.appendChild(child);

		return child;
	}

	public String getElementText(String xmlPath) {
		if (xmlDoc == null) {
			return null;
		}

		return getElementTextByPath(xmlDoc.getDocumentElement(), xmlPath);
	}

	public Node deleteElement(String xmlPath) {
		if (xmlDoc == null) {
			return null;
		}

		return deleteElementByPath(xmlDoc.getDocumentElement(), xmlPath);
	}

	public NodeList getElementsByTagName(String tagName) {
		if (xmlDoc == null) {
			return null;
		}

		return getElementsByTagName(xmlDoc.getDocumentElement(), tagName);
	}

	/**
	 * 将当前XML内存对象输出为 XML字符串
	 * @throws IOException
	 * @returnXML 字符串
	 */
	public String asXML() throws IOException {
		if (xmlDoc == null) {
			return null;
		}

		StringWriter out = new StringWriter();
		XMLWriter writer = new XMLWriter(out);
		try {
			writer.writeNode(xmlDoc.getDocumentElement(), true);
			writer.flush();
		}
		catch (Exception e) {
			return "";
		}
		return out.toString();
	}

	/**
	 * 输出单个节点XML字串
	 * @param node
	 * 节点
	 * @return
	 * @throws IOException
	 */
	public String asXML(Element node) throws IOException {
		if (node == null) {
			return "";
		}

		StringWriter out = new StringWriter();
		XMLWriter writer = new XMLWriter(out);
		try {
			// 此处才用非格式化的输出
			writer.writeNode(node, false);
			writer.flush();
		}
		catch (Exception e) {
			return "";
		}
		return out.toString();
	}

	public static String getElementTextByPath(Element node, String xmlPath) {
		Element emt = getElementByPath(node, xmlPath);
		if (emt == null) {
			return null;
		}

		return emt.getTextContent();
	}

	public static Element getElementByPath(Element node, String xmlPath) {
		if ((node == null) || StringUtils.isEmpty(xmlPath)) {
			return null;
		}

		String[] pathArray = xmlPath.split("/");
		for (String str : pathArray) {
			node = getElementChild(node, str);

			if (node == null) {
				return null;
			}
		}
		return node;
	}

	private static Element getElementChild(Element parentNode, String childName) {
		if ((parentNode == null) || StringUtils.isEmpty(childName)) {
			return null;
		}

		NodeList ndList = parentNode.getChildNodes();
		if ((ndList == null) || (ndList.getLength() <= 0)) {
			return null;
		}

		Node nd;
		for (int i = 0; i < ndList.getLength(); i++) {
			nd = ndList.item(i);
			if (nd.getNodeName().equals(childName)) {
				if (nd instanceof Element) {
					return (Element) nd;
				}
			}
		}
		return null;
	}

	public static Node deleteElementByPath(Element node, String xmlPath) {
		Element emt = getElementByPath(node, xmlPath);
		if ((emt == null) || (emt.getParentNode() == null)) {
			return null;
		}

		return emt.getParentNode().removeChild(emt);
	}

	public static NodeList getElementsByTagName(Element node, String tagName) {
		if ((node == null) || StringUtils.isEmpty(tagName)) {
			return null;
		}

		return node.getElementsByTagName(tagName);
	}

	public Element createElement(String tagName) {
		if (xmlDoc == null) {
			return null;
		}
		else {
			return xmlDoc.createElement(tagName);
		}
	}

	public Attr createAttribute(String attrName) {
		if (xmlDoc == null) {
			return null;
		}
		else {
			return xmlDoc.createAttribute(attrName);
		}
	}

	public String asXML(boolean bShowVersion) throws IOException {
		if (xmlDoc == null) {
			return null;
		}
		StringWriter out = new StringWriter();
		XMLWriter writer = new XMLWriter(out, bShowVersion);
		try {
			writer.writeNode(xmlDoc.getDocumentElement());
			writer.flush();
		}
		catch (Exception e) {
			return "";
		}
		return out.toString();
	}

	public String asXML(Element node, boolean bShowVersion) throws IOException {
		if (node == null) {
			return "";
		}
		StringWriter out = new StringWriter();
		XMLWriter writer = new XMLWriter(out, bShowVersion);
		try {
			writer.writeNode(node);
			writer.flush();
		}
		catch (Exception e) {
			return "";
		}
		return out.toString();
	}

	public static NodeList selectNodes(String express, Object source) {
		NodeList result = null;
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		try {
			result = (NodeList) xpath.evaluate(express, source, XPathConstants.NODESET);
		}
		catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static Node selectSingleNode(String express, Object source) {
		Node result = null;
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		try {
			result = (Node) xpath.evaluate(express, source, XPathConstants.NODE);
		}
		catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return result;
	}
}
