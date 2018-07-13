<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>withdogSommer</title>
    <!-- <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> -->
    <!-- <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> -->
   <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote-lite.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote-lite.js"></script>
  </head>
    
   <!--  <script>
	$(function(){
		$('#summernote').summernote({
			height: 600,
					
			callbacks: {
				onImageUpload: function(files, editor, welEditable) {
					 for (var i = files.length - 1; i >= 0; i--) {
			            	sendFile(files[i], this);
			            }
		        }
			}
			
		});

	})
	
    
    function sendFile(file, el) {
		var form_data = new FormData();
      	form_data.append('file', file);
      	$.ajax({
        	data: form_data,
        	type: "POST",
        	url: '/common/json/imageUpload',
        	cache: false,
        	contentType: false,
        	enctype: 'multipart/form-data',
        	processData: false,
        	success: function(url) {
        		console.log(url);
          		$(el).summernote('editor.insertImage',url.url);
        	}
      	});
    }

	 function test(){
	    	alert(1);
	    	content = $('#summernote').summernote('code');
	    	alert(content);
	      	
	      }
</script> -->
    
  <body>
 <%-- <jsp:include page="/layout/store-sub-header.jsp" />  --%>
    <div id="summernote"></div>
    
    <!-- <div align="right">
    <button type="button" id="cancel" class="btn" onclick="test();">등록하기</button>
	</div> -->
<%-- 	<jsp:include page="/layout/footer.jsp" />

	<jsp:include page="/common/js.jsp" /> --%>
    
  </body>
</html>