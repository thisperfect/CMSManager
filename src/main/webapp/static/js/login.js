var btnLoading = {
    loading: function (btn) {
        var loadingtext = '<i class="fa fa-spinner fa-spin" style="margin: -10px -35px;"></i>' + btn.attr('loading-text');
        var btntext = btn.text();
        btn.addClass('disabled').html(loadingtext).attr('loading-text', btntext);
    },
    reset: function (btn) {
        var loadingtext = btn.attr('loading-text');
        var btntext = btn.text();
        btn.removeClass('disabled').text(loadingtext).attr('loading-text', btntext);
    }
}

function createErrorMessage(select, msg) {
    select.find("div.error-div").remove();
    var errorId = 'error-' + new Date().getTime();
    var html = '<div class="error-div" id="' + errorId + '"><i class="fa fa-minus-circle"></i> <span>';
    html += msg;
    html += '</span> </div>';
    select.find("div.divide-40").append(html);
    setTimeout(function () {
        select.find("div#" + errorId + "").remove();
    }, 5000);
}

function createCode(id) {
    if (id === 'forgot') {
        createValidationCode('forgot', 'code_forgot');
    } else if (id === 'register') {
        createValidationCode('register', 'code_register');
    }
}

function swapScreen(id) {
    createCode(id);
    $('.visible').removeClass('visible').addClass('hide');
    $('#'+id).removeClass('hide').addClass('visible');
}