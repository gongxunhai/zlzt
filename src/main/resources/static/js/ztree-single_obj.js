


function MyZtree(treeData,treeList,treeSelectName,treeContainer,treeSelectVal) {

//定义变量接收外部标签的id
//treeSelectName:用于选中名称的显示;treeContainer:用于存放树列表的div；
// treeList：用于展示树的内容；treeSelectVal：用于存放选中的某项内容的id
//     var treeSelectName,treeContainer,treeList,treeSelectVal;
    this.treeList=treeList;
    this.treeSelectName=treeSelectName;
    this.treeContainer=treeContainer;
    this.treeSelectVal=treeSelectVal;
    this.treeData=treeData;

    // this.initZTree=function () {
    //     $.fn.zTree.init($("#"+this.treeList), this.setting, this.treeData);
    // }

    this.setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick,
            onCheck: onCheck
        }
    }
    $.fn.zTree.init($("#"+this.treeList), this.setting, this.treeData);

    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeList);
        zTree.checkNode(treeNode, !treeNode.checked, null, true);
        $("#"+treeSelectName).attr("value", treeNode.name);
        $("#"+treeSelectVal).attr("value", treeNode.id);
        $("#"+treeSelectVal).change();
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

    this.showMenu=function() {
        var selectNameObj = $("#"+this.treeSelectName);
        var selectNameOffset = $("#"+this.treeSelectName).offset();
        $("#"+this.treeContainer).css({left:selectNameOffset.left + "px", top:selectNameOffset.top + selectNameObj.outerHeight() + "px"}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }

    function onBodyDown(event) {
        if (!( event.target.id == treeSelectName || event.target.id == treeContainer || $(event.target).parents("#"+treeContainer).length>0)) {
            hideMenu();
        }
    }
    function hideMenu() {
        $("#"+treeContainer).fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function alertFun () {
        alert(JSON.stringify(treeData));
        alert("===="+JSON.stringify(this.treeData));
    }

}

// function Persone(name,age) {
//     this.name=name;
//     this.age=age;
//
//
//     this.sayAge = function () {
//         this.sayName();
//     }
//     this.sayName=function () {
//         alert(this.name);
//     }
//
// }