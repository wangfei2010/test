package wf.com.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XML 写对象
 * @author WangFei
 */
public class XMLWriter {
	private Writer writer;
	// 节点深度（用于格式化XML字符串）
	private int level = 0;

	public XMLWriter() throws IOException {
		this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
		this.writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	}

	public XMLWriter(Writer writer) throws IOException {
		this.writer = writer;
		this.writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	}

	public XMLWriter(Writer writer, boolean bShowVersion) throws IOException {
		this.writer = writer;

		if (!bShowVersion) {
			return;
		}
		this.writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	}

	public void writeNode(Node node) throws Exception {
		if (this.writer == null) {
			return;
		}

		writeNotFomat(node);
	}

	public void writeNode(Node node, boolean format) throws Exception {
		if (writer == null) {
			return;
		}
		if (format) {
			writeFomat(node);
		}
		else {
			writeNotFomat(node);
		}
	}

	/**
	 * 格式化写xml
	 * @param node
	 * @throws Exception
	 */
	private void writeFomat(Node node) throws Exception {
		if (writer == null) {
			return;
		}

		if (node.getNodeType() == Node.ELEMENT_NODE) {
			// 输出格式化缩进
			for (int i = 0; i < level * 4; i++) {
				this.writer.write(" ");
			}

			this.writer.write("<");
			this.writer.write(node.getNodeName());
			// 输出属性
			NamedNodeMap map = node.getAttributes();
			if (map != null) {
				for (int i = 0; i < map.getLength(); i++) {
					if (map.item(i).getNodeType() != Node.ATTRIBUTE_NODE) {
						continue;
					}

					this.writer.write(" ");
					this.writer.write(map.item(i).getNodeName());
					this.writer.write("=\"");
					this.writer.write(map.item(i).getNodeValue());
					this.writer.write("\"");
				}
			}
			this.writer.write(">\n");
		}

		NodeList childs = node.getChildNodes();

		// 递归输出子节点
		for (int i = 0; i < childs.getLength(); i++) {
			if (childs.item(i).getNodeType() == Node.ELEMENT_NODE) {
				level++;
				this.writeFomat(childs.item(i));
			}
			else if (childs.item(i).getNodeType() == Node.TEXT_NODE) {
				if (!childs.item(i).getTextContent().equals("")) {
					// 输出格式化缩进
					for (int k = 0; k < (level + 1) * 4; k++) {
						this.writer.write(" ");
					}
					this.writer.write(childs.item(i).getTextContent() + "\n");
				}
			}
		}
		// 输出结束符
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			// 输出格式化缩进
			for (int i = 0; i < level * 4; i++) {
				this.writer.write(" ");
			}

			this.writer.write("</");
			this.writer.write(node.getNodeName());
			this.writer.write(">\n");

			if (level > 0) {
				level--;
			}
		}
	}

	/**
	 * 非格式化写xml
	 * @param node
	 * @throws Exception
	 */
	private void writeNotFomat(Node node) throws Exception {
		if (writer == null) {
			return;
		}

		if (node.getNodeType() == Node.ELEMENT_NODE) {

			this.writer.write("<");
			this.writer.write(node.getNodeName());
			// 输出属性
			NamedNodeMap map = node.getAttributes();
			if (map != null) {
				for (int i = 0; i < map.getLength(); i++) {
					if (map.item(i).getNodeType() != Node.ATTRIBUTE_NODE) {
						continue;
					}

					this.writer.write(" ");
					this.writer.write(map.item(i).getNodeName());
					this.writer.write("=\"");
					this.writer.write(map.item(i).getNodeValue());
					this.writer.write("\"");
				}
			}
			this.writer.write(">");
		}

		NodeList childs = node.getChildNodes();

		// 递归输出子节点
		for (int i = 0; i < childs.getLength(); i++) {
			if (childs.item(i).getNodeType() == Node.ELEMENT_NODE) {
				this.writeNotFomat(childs.item(i));
			}
			else if (childs.item(i).getNodeType() == Node.TEXT_NODE) {
				if (!childs.item(i).getTextContent().equals("")) {
					this.writer.write(childs.item(i).getTextContent());
				}
			}
		}
		// 输出结束符
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			// 输出格式化缩进
			for (int i = 0; i < level * 4; i++) {
				this.writer.write(" ");
			}

			this.writer.write("</");
			this.writer.write(node.getNodeName());
			this.writer.write(">");

		}
	}

	public void flush() throws IOException {
		this.writer.flush();
	}
}
