$.ajax({
    type : "get",
    url : "/kjResults/foreRequest",
    async : false,
    data :{
        "tableName"  :  "kj_result",
        "tableName2" : "kj_result_classifyInfo",
        "status" : 1
    },
    success :function (result) {
        $("#kjResult").html(result.data.length);
    }
});
$.ajax({
    type : "get",
    url : "/zlztDatainfos",
    async : false,
    data :{
        "fromTable"  :  "view_zlztdata"
    },
    success :function (result) {
        $("#putongZl").html(result.data.length);
    }
});
$.ajax({
    type : "get",
    url : "/zlztDatainfos",
    async : false,
    data :{
        "fromTable"  :  "view_zlztdata",
        "secret" : "secret"
    },
    success :function (result) {
        $("#guofangZl").html(result.data.length);
    }
});