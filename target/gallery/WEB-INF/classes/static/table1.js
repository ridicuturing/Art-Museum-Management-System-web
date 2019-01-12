
if(window.location.href.split("//")[1].split("/")[window.location.href.split("//")[1].split("/").length-1] != "login.html")
    $.get("getMyInformation",function(data){if(data=="")window.location = "login.html";});
function searchArtworkByName1(i){
    var url="searchArtworkByName"+i
    var searchTableString='getPage Error';
    var name=$("#searchArtworkByName").val();
    $.ajaxSettings.async = false;
    $.get(
        url,
        {"name":name},
        function (data) {
                searchTableString += "<caption>信息</caption>";
                searchTableString += '<thead><tr><th>id</th><th>名称</th><th>作者</th><th>网络地址</th><th>加入时间</th><th>操作</th></tr></thead><tbody>'
                data.forEach(function (e) {
                    searchTableString += '<tr><th>';
                    searchTableString += e.id;
                    searchTableString += '</th>';
                    searchTableString += '<th>';
                    searchTableString += e.name;
                    searchTableString += '</th>';
                    searchTableString += '<th>';
                    searchTableString += e.painter;
                    searchTableString += '</th>';
                    searchTableString += '<th>';
                    searchTableString += '<a href=' + e.artworkUrl + '>' + e.artworkUrl + '</a>';
                    searchTableString += '</th>';
                    searchTableString += '<th>';
                    searchTableString += e.date;
                    searchTableString += '</th>';
                    searchTableString += '<th>';
                    searchTableString += '<button type="button" onclick="jump(\'/artwork.html?' + e.id + '\')"class="btn btn-default">详情</button>';
                    searchTableString += '<button type="button" onclick="delArtwork('+e.id+')" class="btn btn-default">删除</button>';
                    searchTableString += '</th></tr>';
                });
                searchTableString += '</tbody>';
            }
    );
    $("#searchTable").html(searchTableString);
};
function searchArtworkByPainter1(i){
    var url="searchArtworkByPainter"+i
    var searchTableString='getPage Error';
    var name=$("#searchPainter").val();
    $.ajaxSettings.async = false;
    $.get(
        url,
        {"name":name},
        function (data) {
            qwe = data;
            searchTableString += "<caption>信息</caption>";
            searchTableString += '<thead><tr><th>id</th><th>名称</th><th>作者</th><th>网络地址</th><th>加入时间</th><th>操作</th></tr></thead><tbody>'
            data.forEach(function (e) {
                searchTableString += '<tr><th>';
                searchTableString += e.id;
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += e.name;
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += e.painter;
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += '<a href=' + e.artworkUrl + '>' + e.artworkUrl + '</a>';
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += e.date;
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += '<button type="button" onclick="jump(\'/artwork.html?' + e.id + '\')"class="btn btn-default">详情</button>';
                searchTableString += '<button type="button" onclick="delArtwork('+e.id+')" class="btn btn-default">删除</button>';
                searchTableString += '</th></tr>';
            });
            searchTableString += '</tbody>';
        }
    );
    $("#searchTable").html(searchTableString);
};
function searchByManager(i){
    var url= "searchByManager"+i
    var searchTableString='getPage Error';
    var name=$("#managerName").val();
    $.ajaxSettings.async = false;
    $.get(
        url,
        {"name":name},
        function (data) {
            qwe = data;
            searchTableString += "<caption>信息</caption>";
            searchTableString += '<thead><tr><th>id</th><th>名称</th><th>作者</th><th>网络地址</th><th>加入时间</th><th>操作</th></tr></thead><tbody>'
            data.forEach(function (e) {
                searchTableString += '<tr><th>';
                searchTableString += e.id;
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += e.name;
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += e.painter;
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += '<a href=' + e.artworkUrl + '>' + e.artworkUrl + '</a>';
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += e.date;
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += '<button type="button" onclick="jump(\'/artwork.html?' + e.id + '\')"class="btn btn-default">详情</button>';
                searchTableString += '<button type="button" onclick="delArtwork('+e.id+')" class="btn btn-default">删除</button>';
                searchTableString += '</th></tr>';
            });
            searchTableString += '</tbody>';
        }
    );
    $("#searchTable").html(searchTableString);
};
function searchPicturesByManager(i){
    var url= "searchPicturesByManager"+i
    var searchTableString='getPage Error';
    var name=$("#managerName2").val();
    $.ajaxSettings.async = false;
    $.get(
        url,
        {"name":name},
        function (data) {
            searchTableString += "<caption>展品</caption>";
            searchTableString += '<thead><tr><th>id</th><th>关联的艺术品id</th><th>管理人员id</th><th>描述</th><th>加入时间</th><th>url</th><th>操作</th></tr></thead><tbody>'
            data.forEach(function (e) {
                searchTableString += '<tr><th>';
                searchTableString += e.id;
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += e.artworkId;
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += e.managerId;
                searchTableString += '</th>';
                searchTableString += '<th>';
                if(e.describe==null)
                    searchTableString +='无';
                else
                    searchTableString += e.describe;
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += e.date;
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += '<a href=' + e.url + '>' + e.url + '</a>';
                searchTableString += '</th>';
                searchTableString += '<th>';
                searchTableString += '<input type="button" value="详情" onclick="jump(\'/picture.html?' + e.id + '\')"class="btn btn-default"></input>';
                searchTableString += '<input type="button" value="删除" onclick="delPicture('+e.id+')" class="btn btn-default"></input>';
                searchTableString += '</th></tr>';
            })
        }
    );
    $("#searchTable").html(searchTableString);
};

function logout() {
    document.cookie = "loginCredentials=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
}
function addVisitor(){
    visitorName = $("#visitorName").val();
    note = $("#note").val();
    $.get("addVisitors",{"name":visitorName,"note":note},function (data) {
        if(data==true)
            alert("登记成功");
        else
            alert("登记失败");
        window.location="visitorListPage.html";
    })
}
function addManager(){
    var name=$("#managerName").val();
    var account=$("#account").val();
    var password=$("#password").val();
    var position=$("#position").val();
    var grade=$("#grade").val();
    $.post(
        "addManager",
        {"name":name,"account":account,"password":password,"position":position,"grade":grade,},
        function (data) {
            switch (data){
                case 1:alert("添加成功");
                    window.location="userManagePage.html";
                    break;
                case -3:alert("账号已存在");
                    break;
                case 0:alert("添加失败");
                    break;
                default:alert("添加失败");
                    break;
            };
        }
    );
}
function jump(u){
    window.location = u;
};
function addArtwork(){
    $.get(
         "addArtwork",
        {"name":$("#artWorkName").val(),"painter":$("#painter").val(),"describe":$("#describe").val()},
        function(data){
            switch(data){
                case -2:alert("请重新登录");
                    break;
                case -1:alert("此美术品已存在");
                    break;
                case 0:alert("数据库更新失败");
                    break;
                case 1:alert("添加美术品成功");
                    break;
                default:alert(data);
            }
        }
    )
};
function modifyArtwork(){
    var id=window.location.href.split("?")[1]
    $.post(
         "modifyArtwork",
        {"artwordId":id,"name":$("#artWorkName").val(),"painter":$("#painter").val(),"artworkUrl":$("#artworkUrl").val(),"describe":$("#describe").val()},
        function(data){
            switch(data){
                case -2:alert("请重新登录");
                    break;
                case 0:alert("数据库更新失败");
                    break;
                case 1:alert("修改美术品成功");
                    window.location="artwork.html?"+id;
                    break;
                default:alert(data);
            }
        }
    );
};
function modifyPicture(){
    $.post(
         "modifyPicture",
        {"url":$("#url").val(),"describe":$("#describe").val(),"artworkId":$("#artworkId").val()},
        function(data){
            switch(data){
                case -2:alert("请重新登录");
                    break;
                case 0:alert("数据库更新失败");
                    break;
                case 1:alert("修改信息成功");
                    jump("pictureListPage.html")
                    break;
                default:alert(data);
            }
        }
    );
};
function delArtwork(i){
    var r=confirm("美术品id:" + i + "请确认删除!");
    if (r==true){
        $.get(
             "delArtwork",
            {"artworkId":i},
            function(data){
                switch(data){
                    case -2:alert("请重新登录");
                        break;
                    case 0:alert("此美术品不存在");
                        break;
                    case 1:alert("删除美术品成功");
                        jump("artworksListPage.html");
                        break;
                    default:alert(data);
                }
            }
        );
    }
};
function delManager(myId,myGrade,itId,itGrade,name){
    if(itId == myId){
        alert("不可以注销自己账户");
        return;
    }
    if(itGrade >= myGrade){
        alert("只允许删除比你权限低的用户");
        return;
    }
    var r=confirm("请确认删除!");
    if (r==true) {
        $.get(
             "delManager",
            {"id":itId},
            function (data) {
                switch (data){
                    case -2:alert("登录过期，请重新登录！");
                        break;
                    case -1:alert("删除用户:" + name + "失败（此用户不存在）");
                        break;
                    case 0:alert("不允许删除自己账户！");
                        break;
                    case 1:alert("删除用户:" + name + "成功");
                        location.reload();
                        break;
                    default:alert(data);
                }
            }
        );
    }

};
function delPicture(i) {
    var r = confirm("图片id:" + i + "请确认删除!");
    if (r == true) {
        $.get(
             "delPicture",
            {"pictureId": i},
            function (data) {
                switch (data) {
                    case -2:
                        alert("请重新登录");
                        break;
                    case 0:
                        alert("此图片不存在");
                        break;
                    case 1:
                        alert("删除图片成功");
                        jump("pictureListPage.html");
                        break;
                    default:
                        alert(data);
                }
            }
        );
    }
};
function login(){
    $.ajax({
        url: "login",
        data:{"account":$("#entry_name").val(),"password":$("#entry_password").val()},
        success:function (data) {
            if(data==-1)
                alert("用户名密码错误");
            if(data==1)
                window.location = "artworksListPage.html";
        },
        async:true
    });
};
function loadNavbar(){
    var id;
    $.ajaxSettings.async = false;
    $.get("getMyInformation",function (data) {id=data.id;});
    $("#myNavbar").html('<ul id="menu" class="nav navbar-nav">\n' +
        '        <li><a id="artworksListPage"href="artworksListPage.html">展品管理</a></li>\n' +
        '        <li><a id="userManagePage" href="userManagePage.html">用户管理</a></li>\n' +
        '        <li><a id="addManagerPage" href="addManagerPage.html">添加管理员</a></li>\n' +
        '        <li><a id="addArtworkPage" href="addArtworkPage.html">添加展品</a></li>\n' +
        '        <li><a id="" href="addPicturePage.html">添加图片</a></li>\n' +
        '        <li><a id="" href="pictureListPage.html">所有图片查看</a></li>\n' +
        '        <li><a id="" href="search.html">查找</a></li>\n' +
        '        <li><a id="" href="addVisitor.html">访客登记</a></li>\n' +
        '        <li><a id="" href="visitorListPage.html">访客记录</a></li>\n' +
        '        <li><a onclick="logout()" id="" href="login.html">当前id:' + id + '(点击注销)</a></li>\n' +
        '      </ul>');
};

function searchUrlByManagerName(){
    var name=$("#searchUrlByManagerName").val();
    $.get(
         "searchUrlByManagerName",
        {"name":name},
        function (data) {
            searchUrlByManagerName(data);
        }
    );
};
$(document).ready(function()  {
    loadNavbar();
    $.ajaxSettings.async = false;

});
