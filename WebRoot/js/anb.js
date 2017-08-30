//JS�ж�ͼ��ҳ���Ƿ�Ϊ�պ��Ƿ�Ϊ����
function check(str){
	var temp = "";
	for(var i = 0; i < str.length; i++){
		if(str.charCodeAt(i) >= 48 && str.charCodeAt(i) <= 57)
			temp += str.charAt(i);
	}
	temp = parseInt(temp);
	
	if(temp > 9999)
		temp = 9999;
		
	if(isNaN(temp))
		temp = 0;
	return temp;
}

//���ͼ��۸��Ƿ�Ϊ����
function checkFloat(str){
	var temp = "";
	var k = 0;
	for(var i = 0; i < str.length; i++){
		if(str.charCodeAt(i) >= 48 && str.charCodeAt(i) <= 57){
			temp += str.charAt(i);
		}else if(str.charCodeAt(i)==46&&k<=0){
			temp +=str.charAt(i);
			k++;
		}
	}
	temp = parseFloat(temp);
	
	if(temp > 9999)
		temp = 9999;
		
	if(isNaN(temp))
		temp = 0;
	return temp;
}

//JS�ж�ͼ��ҳ���Ƿ�Ϊ�պ��Ƿ�Ϊ����
function checkBookWordCount(){
	var bookWordCount = document.getElementById("BookWordCount").value;
	var $checkBookWordCountInfo = $("#checkBookWordCountInfo");
	$checkBookWordCountInfo.html("");
	if (bookWordCount=='') 
	{
		$checkBookWordCountInfo.html("*����Ϊ��").css('color','#FF5151');
	}else if(bookWordCount%1!=0){
		$checkBookWordCountInfo.html("*����Ϊ����").css('color','#FF5151');
	}else if(bookWordCount.indexOf('.')>-1){
		$checkBookWordCountInfo.html("*����������������").css('color','#FF5151');
		
	}
}

//JS�ж��޸������Ƿ�Ϊ��
function checkEmpty(name){
	var book = document.getElementById(name).value;
	var $checkInfo = $("#check"+name+"Info");
	$checkInfo.html("");
	if (book=='') 
	{
// alert("username is required");
		$checkInfo.html("*����Ϊ��").css('color','#FF5151');
// document.getElementById("submit").disabled = true;
	}
}

//�ж�ѡ����ļ��Ƿ�ΪͼƬ
function changeImage(img){
	var url = img.value; 
    var fileext=url.substring(url.lastIndexOf("."),url.length);      
    fileext=fileext.toLowerCase()
    
    if((fileext!='.jpg')&&(fileext!='.gif')&&(fileext!='.jpeg')&&(fileext!='.png')&&(fileext!='.bmp')&&fileext!=null){   
        alert("Wrong Image!");
        document.applyForm.upload.focus();
    }else{
    	var element = document.getElementById('bookImage');
    	var imgPath = window.URL.createObjectURL(img.files[0]); 
    	element.src = imgPath; 
    }
}

//���ð�ť
function resetBook(){
	location.href="AddNewBook.jsp";
}

//ȡ����ť
function cancel(){
	location.href="manageBook.action";
}