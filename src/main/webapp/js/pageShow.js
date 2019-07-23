(function (w, d, u) {
    var showContent = util.get('showContent');
    if (!showContent) {
        return;
    }
    var loading = new Loading();
    var layer = new Layer();
    var addToCart = util.get('addToCart');
    var page = {
        init: function () {
            addToCart.addEventListener('click', function (e) {
                var ele = e.target;
                var contentId = ele && ele.dataset.buy;
                var buyNum = $('allNum').textContent;
                if (contentId) {
                    layer.reset({
                        content: '确认购买本内容吗？',
                        onconfirm: function () {
                            layer.hide();
                            loading.show();
                            ajax({
                                data: {contentId: contentId, buyNum: buyNum},
                                url: '/addToCart',
                                dataType: "json",
                                success: function (result) {
                                    loading.result('已加入购物车');
                                },
                                error: function (message) {
                                    loading.result(message || '加入购物车失败');
                                }
                            });
                        }.bind(this)
                    }).show();
                    return;
                }
            }.bind(this), false);
        }
    };
    page.init();
})(window, document);

var $ = function (id) {
    return document.getElementById(id);
}


$('minusNum').onclick = function (e) {
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = $('allNum').textContent;
    if (num > 1) {
        num--;
        $('allNum').innerHTML = num;
    } else {
        alert("您没有购买任何商品");
    }
};

$('plusNum').onclick = function (e) {
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = $('allNum').textContent;
    num++;
    $('allNum').innerHTML = num;

};

