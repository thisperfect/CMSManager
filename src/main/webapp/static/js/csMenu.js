var menu = document.querySelector('.csmenu');
var csMenu = {
    getCurrentTabId: function () {
       return $('ul#layui-tab-cs-menu  li.layui-this').attr('lay-id');
    },
    getCurrentTabContent: function () {
       return $('div.content-wrapper > div.layui-tab-card div.layui-tab-content div.layui-show');
    },
    refresh: function () {
        setTimeout(function () {
            var _id = csMenu.getCurrentTabId();
            if ('bhome' === _id) {
                return;
            }
            addContent($('#' + _id), false, csMenu.getCurrentTabContent());
        },50);
    },
    close: function () {
        var _id = csMenu.getCurrentTabId();
        if ('bhome' === _id) {
            return;
        }
        active.tabDelete(_id);
    },
    closeAll: function () {
        $('ul#layui-tab-cs-menu li').each(function () {
            var _id = $(this).attr('lay-id');
            if (_id != 'bhome') {
                active.tabDelete(_id);
            }
        })
    },
    closeLeft: function () {
        $('ul#layui-tab-cs-menu  li.layui-this').prevAll().each(function () {
            var _id = $(this).attr('lay-id');
            if (_id != 'bhome') {
                active.tabDelete(_id);
            }
        });
    },
    closeRight: function () {
        $('ul#layui-tab-cs-menu  li.layui-this').nextAll().each(function () {
            var _id = $(this).attr('lay-id');
            active.tabDelete(_id);
        });
    },
    closeOther: function () {
       var _currId = csMenu.getCurrentTabId();
        $('ul#layui-tab-cs-menu li').each(function () {
            var _id = $(this).attr('lay-id');
            if (_id != 'bhome' && _id != _currId) {
                active.tabDelete(_id);
            }
        });
    },
    showMenu: function (x, y) {
        menu.style.display = 'block';
        menu.style.left = x + 'px';
        menu.style.top = y + 'px';
        menu.classList.add('show-menu');
    },
    hideMenu: function () {
        menu.classList.remove('show-menu');
    },
    onContextMenu: function (e) {
        e.preventDefault();
        csMenu.showMenu(e.pageX, e.pageY);
        document.addEventListener('mousedown', csMenu.onMouseDown, false);
    },
    onMouseDown: function (e) {
        var $this = $(e.target);
        var _type = $this.attr('data-type');
        if (_type) {
            csMenu[_type].call($this);
        }
        csMenu.hideMenu();
        document.removeEventListener('mousedown', csMenu.onMouseDown);
    }
}
document.addEventListener('contextmenu', csMenu.onContextMenu, false);

