// 当前登录用户可以访问的菜单Map
/*var menuMap = {};

$(function() {
	common.ajax({
			url : $("#basePath").val() + "/session/menus",
			success : function(data) {
				if(data && data.length > 0) {
					$.each(data,function(i,value) {
						if(!menuMap[value.parentId]) {
							menuMap[value.parentId] = new Array();
						}
						menuMap[value.parentId].push(value);
					});
					initMenu();
				}
			}
	});
});*/

/**
 * 初始化菜单
 */
/*function initMenu() {
	var menuList = menuMap[0];
	$("#mainMenuUl").html("");
	$.each(menuList,function(i,value) {
		$("#mainMenuUl").append("<li onclick='clickFirstMenu(this," + value.id + ")'><a><span>" + value.name + "</span></a></li>");
	});
}*/

/**
 * 根据父菜单ID初始化子菜单
 */
/*function initSubMenu(parentId) {
	var menuList = menuMap[parentId];
	$("#MenuDiv").html("");
	$.each(menuList,function(i,value) {
		$("#MenuDiv").append("<h3 onclick=\"clickSecondMenu(this,'" + value.url + "')\"><a>" + value.name + "</a></h3>");
	});
}*/

/**
 * 方法描述:单击菜单（页面上部菜单），初始化子菜单（即页面左部菜单）
 */
function clickFirstMenu(element) {
	//判断当前单击的几点是否为[选中样式]，如果已经是[选中样式]，不在触发
	if($(element).attr("class")!="on"){
	// 将同级节点的[选中样式]清空
	$("#mainmenuUI").children().attr("class","");
	// 将当前单击的节点置为[选中样式]
	$(element).attr("class","on");
	// 加载二级菜单内容
	$("menuDiv").html("<h3 onclick='clickSecondMenu(this)'><a>广告管理</a></h3><h3 onclick='clickSecondMenu'></h3>")
	}
}

/**
 * 方法描述:单击子菜单（页面左部菜单），初始化主页面
 */
function clickSecondMenu(element,path) {
	// 将其他有[选中样式]的节点的样式清空
	$("#MenuDiv").find(".on").attr("class","");
	// 将当前单击的节点置为[选中样式]
	$(element).children().attr("class","on");
	// 按指定地址加载主页面(iframe)
	$("#mainPage").attr("src",path);
}

/**
* 打开密码修改弹出层
*/
function openAddDiv(){
	$("#mengban").css("visibility","visible");
	$(".wishlistBox").show();
	$(".wishlistBox").find(".persongRightTit").html("&nbsp;&nbsp;修改密码");
	$("#submitVal").show();
}

/**
* 关闭密码修改弹出层
*/
function closeDiv(){
	$("#mengban").css("visibility","hidden");
	$("#oldPassword").val("");
	$("#newPassword").val("");
	$("#newPasswordAgain").val("");
	$(".wishlistBox").hide();
}