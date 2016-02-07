$('div.input-group-btn ul.dropdown-menu li a').click(function(e) {
    // var $div = $(this).parent().parent().parent();
    var $div = $('#pnlMenu');
    $div.removeClass('open');
    if (!this.id) {
        return;
    }

    var lblAction = $('#lblAction');
    lblAction.html($(this).text());

    var btnGo = $('#btnGo');
    btnGo.removeAttr("disabled");

    console.log("selected id - " + this.id);
    changeView(this.id);
    e.preventDefault();
    return false;
});

var currentAction;
var contextName = '/VotingSystem4Lunch';

var pnlBigRequest = $('#pnlBigRequest');
var txtBigRequest = $('#txtBigRequest');
var txtSmallRequest = $('#txtSmallRequest');
var txtBigResponse = $('#txtBigResponse');
var pCnt = $('#pCnt');
var pNoAuth = $('#pNoAuth');

var showNoAuth = function(show) {
    if(show) {
        pNoAuth.show();
    } else {
        pNoAuth.hide();
    }
};

var changeView = function(typeView) {
    currentAction = typeView;

    txtBigRequest.val('');
    txtBigResponse.val('');
    pCnt.text('');

    if (typeView == 'getByPage' || typeView == 'getByPageRoles') {
        var page = {
            start : 0,
            size : 10,
            findingValue : '',
            sortingField : '',
            sortAsc : true
        };

        txtBigRequest.val(JSON.stringify(page, null, '  '));
        pnlBigRequest.show();
        txtBigRequest.show();
        pCnt.show();
        txtSmallRequest.hide();
    } else if (typeView == 'getUser' || typeView == 'getRole' || typeView == 'getByUserRoles') {
        pnlBigRequest.show();
        txtBigRequest.hide();
        pCnt.hide();
        txtSmallRequest.show();
    } else if (typeView == 'login' || 
            typeView == 'createUser' || typeView == 'createRole' || typeView == 'createRoleUser' || typeView == 'deleteRoleUser') {
        var reqObj = {
            id : null,
            name : 'a',
            login : 'b',
            password : 'c',
            hashedPassword : ''
        };

        if (typeView == 'createRole') {
            reqObj = {
                id : null,
                name : 'a',
                admin : false
            };
        } else if (typeView == 'createRoleUser' || typeView == 'deleteRoleUser') {
            reqObj = {
                userId : 1,
                roleId : 2
            };
        } else if (typeView == 'login') {
            reqObj = {
                username : 'user1',
                password : 'user2',
            };
        }

        txtBigRequest.val(JSON.stringify(reqObj, null, '  '));

        pnlBigRequest.show();
        txtBigRequest.show();
        pCnt.hide();
        txtSmallRequest.hide();
    } else if (typeView == 'updateUser' || typeView == 'updateRole') {
        var reqObj = {
            id : 6,
            name : 'a6',
            login : 'b6',
            password : 'c6'
        };
        if (typeView == 'updateRole') {
            reqObj = {
                id : 1,
                name : 'a',
                admin : false
            };
        }

        txtBigRequest.val(JSON.stringify(reqObj, null, '  '));

        pnlBigRequest.show();
        txtBigRequest.show();
        pCnt.hide();
        txtSmallRequest.hide();
    } else if (typeView == 'deleteUser' || typeView == 'deleteRole') {
        pnlBigRequest.show();
        txtBigRequest.hide();
        pCnt.hide();
        txtSmallRequest.show();
    } else {
        pnlBigRequest.hide();
    }

};

changeView('getByPage');
$('#lblAction').html('Get users by the page');
var btnGo = $('#btnGo');
btnGo.removeAttr("disabled");
showNoAuth(false);

var post = function(url, data, succF) {
    $.ajax({
        url : url,
        type : "post",
        data : data,
        dataType : "json",
        contentType : "application/json",
        success : succF
    }).fail(function(xhr) {
        if(xhr.status == 401) {
            showNoAuth(true);
        }
    });
};
var put = function(url, data, succF) {
    $.ajax({
        url : url,
        type : "put",
        data : data,
        dataType : "json",
        contentType : "application/json",
        success : succF
    }).fail(function(xhr) {
        if(xhr.status == 401) {
            showNoAuth(true);
        }
    });
};
var delet = function(url, succF) {
    $.ajax({
        url : url,
        type : "delete",
        success : succF
    }).fail(function(xhr) {
        if(xhr.status == 401) {
            showNoAuth(true);
        }
    });
};
var delet2 = function(url, data, succF) {
    $.ajax({
        url : url,
        type : "delete",
        data : data,
        dataType : "json",
        contentType : "application/json",
        success : succF
    }).fail(function(xhr) {
        if(xhr.status == 401) {
            showNoAuth(true);
        }
    });
};

// actions
$('#btnGo').click(function(e) {
    console.log('selected action is', currentAction);

    if (currentAction == 'getByPage' || currentAction == 'getByPageRoles') {
        console.log('request is', txtBigRequest.val());

        // set urls
        var url = contextName + '/api/v1/user';
        var countUrl = contextName + '/api/v1/user/count';
        if (currentAction == 'getByPageRoles') {
            url = contextName + '/api/v1/role';
            countUrl = contextName + '/api/v1/role/count';
        }

        var page = JSON.parse(txtBigRequest.val());

        // get entities
        // "json=" + txtBigRequest.val()
        $.getJSON(url, page, function(data) {
            txtBigResponse.val(JSON.stringify(data, null, '  '));
        }).fail(function(xhr) {
            if(xhr.status == 401) {
                showNoAuth(true);
            }
        });

        // get count
        if (page.findingValue) {
            countUrl += "/" + page.findingValue;
        }
        $.getJSON(countUrl, function(data) {
            pCnt.text(JSON.stringify(data, null, '  '));
        });
    } else if (currentAction == 'getUser' || currentAction == 'getRole' || currentAction == 'getByUserRoles') {
        console.log('request is', txtSmallRequest.val());

        var url = contextName + '/api/v1/user/id/' + txtSmallRequest.val();
        if (currentAction == 'getRole') {
            url = contextName + '/api/v1/role/id/' + txtSmallRequest.val();
        } else if (currentAction == 'getByUserRoles') {
            url = contextName + '/api/v1/role/user/' + txtSmallRequest.val();
        }

        $.getJSON(url, function(data) {
            txtBigResponse.val(JSON.stringify(data, null, '  '));
        }).fail(function(xhr) {
            if(xhr.status == 401) {
                showNoAuth(true);
            }
        });
    } else if (currentAction == 'createUser' || currentAction == 'createRole' || currentAction == 'createRoleUser') {
        console.log('request is', JSON.parse(txtBigRequest.val()));
        var url = contextName + '/api/v1/user';
        if (currentAction == 'createRole') {
            url = contextName + '/api/v1/role';
        } else if (currentAction == 'createRoleUser') {
            url = contextName + '/api/v1/role/user';
        }

        post(url, txtBigRequest.val(), function(data) {
            txtBigResponse.val(JSON.stringify(data, null, '  '));
            console.log('nice');
        });
    } else if (currentAction == 'deleteRoleUser') {
        console.log('request is', JSON.parse(txtBigRequest.val()));
        var url = '/api/v1/role/user';

        delet2(contextName + url, txtBigRequest.val(), function(data) {
            txtBigResponse.val('nice');
        });
    } else if (currentAction == 'updateUser' || currentAction == 'updateRole') {
        console.log('request is', JSON.parse(txtBigRequest.val()));

        var url = contextName + '/api/v1/user';
        if (currentAction == 'updateRole') {
            url = contextName + '/api/v1/role';
        }

        put(url, txtBigRequest.val(), function(data) {
            txtBigResponse.val(JSON.stringify(data, null, '  '));
            console.log('nice update');
        });
    } else if (currentAction == 'deleteUser' || currentAction == 'deleteRole') {
        console.log('delete id is', txtSmallRequest.val());

        var url = '/api/v1/user/';
        if (currentAction == 'deleteRole') {
            url = '/api/v1/role/';
        }

        delet(contextName + url + txtSmallRequest.val(), function(data) {
            txtBigResponse.val('nice');
        });
    } else if (currentAction == 'login') {
        console.log('request is', JSON.parse(txtBigRequest.val()));
        var url = '/login';
        
        var page = JSON.parse(txtBigRequest.val());

        $.post(contextName + url, page, function(data) {
            txtBigResponse.val('Login was successful');
            showNoAuth(false);
        });
    } else if (currentAction == 'logout') {
        console.log('logout');
        var url = '/logout';
        
        $.get(contextName + url, function(data) {
            txtBigResponse.val('Logout was successful');
            showNoAuth(true);
        });
    }
});