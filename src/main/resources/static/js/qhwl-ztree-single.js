//定义变量接收外部标签的id
//treeSelectName:用于选中名称的显示;treeContainer:用于存放树列表的div；
// treeList：用于展示树的内容；treeSelectVal：用于存放选中的某项内容的id
var treeSelectName,treeContainer,treeList,treeSelectVal;

var setting = {
    // check: {
    //     enable: true,
    //     chkStyle: "radio",
    //     radioType: "all"
    // },
    // view: {
    //     dblClickExpand: false
    // },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: onClick,
        onCheck: onCheck
    }
};


function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeList);
    zTree.checkNode(treeNode, !treeNode.checked, null, true);
    $("#"+treeSelectName).attr("value", treeNode.name);
    $("#"+treeSelectVal).attr("value", treeNode.id);
    hideMenu();
    return false;
}

function onCheck(e, treeId, treeNode) {

    var zTree = $.fn.zTree.getZTreeObj(treeList),
        nodes = zTree.getCheckedNodes(true),
        v = "";
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].name + ",";
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    $("#"+treeSelectName).attr("value", treeNode.name);
    $("#"+treeSelectVal).attr("value", treeNode.id);
    hideMenu();
}

function showMenu() {
    var cityObj = $("#"+treeSelectName);
    var cityOffset = $("#"+treeSelectName).offset();
    $("#"+treeContainer).css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
    $("#"+treeContainer).fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!( event.target.id == treeSelectName || event.target.id == treeContainer || $(event.target).parents("#"+treeContainer).length>0)) {
        hideMenu();
    }
}

function initZTree(treeData,treeList,treeSelectName,treeContainer,treeSelectVal) {
    this.treeList=treeList;
    this.treeSelectName=treeSelectName;
    this.treeContainer=treeContainer;
    this.treeSelectVal=treeSelectVal;
    $.fn.zTree.init($("#"+treeList), setting, treeData);
}
