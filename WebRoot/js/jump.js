/* ��ת����ǰҳ�棬������ֵΪ�գ�Ĭ����ת����ҳ*/
function jumpPreLocation(preLocation){
	if(preLocation != "null"){
		location.href = preLocation;
	}else{
		location.href = "http://localhost:8080/BookStore/HomePage.jsp";
	}
}

/*��ת����¼����*/
function jumpToLogin(){
		location.href="jumpToLogin?locationHref=" + location.href;
}
/*��ת��ע������*/
function jumpToLogoff(){
	location.href="jumpToLogoff?locationHref=" + location.href;
}
/*��ת��ע�ᴦ��*/
function jumpToRegister(){
	location.href="jumpToRegister?locationHref=" + location.href;
}
/*��ת���޸����ϴ���*/
function jumpToReviseData(){
	location.href="jumpToReviseData?locationHref=" + location.href;
}
/*��ת�������ջ���ַ����*/
function jumpToManageConsignmentAddress(){
	location.href="jumpToManageConsignmentAddress?locationHref=" + location.href;
}
/*��ת���޸����봦��*/
function jumpToRevisePassword(){
	location.href="jumpToRevisePassword?locationHref=" + location.href;
}
/*��ת���޸��ջ���ַ����*/
function jumpToReviseConsignmentAddress(id){
	location.href = "jumpToReviseConsignmentAddress?locationHref=" + location.href + "&consignmentAddressId=" + id;
}
/*��ת��ɾ���ջ���ַ����*/
function jumpToDeleteConsignmentAddress(id){
	location.href = "jumpToDeleteConsignmentAddress?locationHref=" + location.href + "&consignmentAddressId=" + id;
}
/*��ת������ջ���ַ����*/
function jumpToAddConsignmentAddress(){
	location.href = "jumpToAddConsignmentAddress?locationHref=" + location.href;
}
/*��ת��ɾ���û�����*/
function jumpToDeleteCustomer(id){
	location.href = "jumpToDeleteCustomer?cusId=" + id;
}
/*��ת���޸��û����ϴ���*/
function jumpToReviseCustomer(id){
	location.href = "jumpToReviseCustomer?locationHref=" + location.href +"&cusId=" + id;
}
/*��ת������Ա��¼����*/
function jumpToAdministratorLogoff(){
	location.href = "jumpToAdministratorLogoff";
}
/*��ȡ��������*/
function getQuantity(){
	return document.getElementById("number").value;
}
/*��ת�����빺�ﳵ����*/
function jumpToAddShopping(id){
	location.href = "http://localhost:8080/BookStore/AddShoppingAction?bookId=" + id + "&quantity=" + getQuantity();
}
