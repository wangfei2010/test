function addTab(title, href){  
    var tt = $('#main-center');  
    if (tt.tabs('exists', title)){  
    	tt.tabs('select', title);  
    } else {  
	    if (href){  
	    	var content = '<iframe scrolling="no" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>';  
	    } else {  
	    	var content = '未实现';  
	    }  
	    tt.tabs('add',{  
	    title:title,  
	    closable:true,  
	    content:content  
	    });  
    }  
}  