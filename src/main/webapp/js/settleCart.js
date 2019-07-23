(function (w, d, u) {
    var table = util.get('newTable');
    if (!table) {
        return;
    }
    var layer = new Layer();
    var loading = new Loading();
    var page = {
        init: function () {
            var that = this;
            table.addEventListener('click', function (e) {
                var ele = e.target;

                var subId = ele.dataset && ele.dataset.sub;
                if (subId) {
                    that.onSub(subId)
                    return;
                }
                var addId = ele.dataset && ele.dataset.add;
                if (addId) {
                    that.onAdd(addId)
                    return;
                }
                var delId = ele.dataset && ele.dataset.del;
                if (delId) {
                    that.onDel(delId);
                    return;
                }
            }, false);
        },
        onDel: function (id) {
            var that = this;
            layer.reset({
                content: '确定要删除该条内容吗？',
                onconfirm: function () {
                    layer.hide();
                    loading.show();
                    ajax({
                        url: '/deleteCart',
                        data: {cartId: id},
                        success: function (result) {
                            that.delCartItemNode(id);
                            loading.result('删除成功');
                        },
                        error: function () {
                            loading.result('删除失败');
                        }
                    });
                }
            }).show();
        },
        delCartItemNode: function (id) {
            var item = util.get('p-' + id);
            var totalPrice = util.get('totalPrice');
            var totalPriceNumber = util.get('totalPrice').textContent;
            var price = util.get('price' + id).textContent;
            console.log('price:' + price);
            var num = util.get('count' + id).textContent;
            console.log('num:' + num);
            var curPrice = totalPriceNumber - price * num;
            console.log('curPrice:' + curPrice);
            if (item && item.parentNode) {
                item.parentNode.removeChild(item);
                totalPrice.innerHTML = curPrice;
            }
        },
        onSub: function (id) {
            var totalPrice = util.get('totalPrice');
            var totalPriceNumber = util.get('totalPrice').textContent;
            var price = util.get('price' + id).textContent;
            var numTag = util.get('count' + id);
            var num = util.get('count' + id).textContent;
            console.log('num:' + num);
            if (num <= 1) {
                alert("商品数量不能再减少了");
            }else {
                var curNum = num - 1;
                var curPrice = totalPriceNumber - price;
                console.log('curPrice:' + curPrice);
                loading.show();
                ajax({
                    url: '/editCartCount',
                    data: {cartId: id, count: curNum},
                    success: function (result) {
                        numTag.innerHTML = curNum;
                        totalPrice.innerHTML = curPrice;
                        loading.result('减少数量成功');
                    },
                    error: function () {
                        loading.result('减少数量失败');
                    }
                });

            }
        },
        onAdd: function (id) {
            var totalPrice = util.get('totalPrice');
            var totalPriceNumber = util.get('totalPrice').textContent;
            var price = util.get('price' + id).textContent;
            var numTag = util.get('count' + id);
            var num = util.get('count' + id).textContent;
            console.log('num:' + num);
            var curNum = Number(num) + 1;
            var curPrice = Number(totalPriceNumber) + Number(price);
            console.log('curPrice:' + curPrice);
            loading.show();
            ajax({
                url: '/editCartCount',
                data: {cartId: id, count: curNum},
                success: function (result) {
                    numTag.innerHTML = curNum
                    totalPrice.innerHTML = curPrice;
                    loading.result('增加数量成功');
                    loading.close()
                },
                error: function () {
                    loading.result('增加数量失败');
                    loading.close()
                }
            });
        }
    };
    util.get('back').onclick = function () {
        window.history.back();
    }

    util.get('settle').onclick = function () {
        layer.reset({
            content: '确定要结算吗？',
            onconfirm: function () {
                layer.hide();
                loading.show();
                ajax({
                    url: '/settle',
                    success: function (result) {
                        loading.result('结算成功');
                        location.href = '/account';
                    },
                    error: function () {
                        loading.result('结算失败');
                    }
                });
            }
        }).show();
    }
    page.init();
})(window, document);







