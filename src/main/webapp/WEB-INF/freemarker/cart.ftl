<!DOCTYPE html>
<html>
<#include "include/head.ftl">
<body>
<#include "include/support.ftl">
<#include "include/header.ftl">
<#assign total = 0>
<div class="g-doc" id="settleAccount">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已添加到购物车的内容</h2>
    </div>
    <table id="newTable" class="m-table m-table-row n-table g-b3">
        <thead>
        <tr>
            <th>内容名称</th>
            <th>数量</th>
            <th>价格</th>
            <th>删除</th>
        </tr>
        </thead>
        <#if cartList?exists && cartList?has_content>

        <tbody>
        <#list cartList as item>
            <#assign total = total + item.content.price*item.count>
        <tr id="p-${item.id}">
            <td>${item.content.title}</td>
            <td>
                <span class="lessNum" id="minusNum" data-sub="${item.id}">-</span>
                <span class="totalNum" id="count${item.id}">${item.count}</span>
                <span class="moreNum" id="plusNum" data-add="${item.id}">+</span>
            </td>
            <td>
                <span class="totalNum" id="price${item.id}">${item.content.price}</span>
            </td>
            <td><span class="u-btn u-btn-normal u-btn-xs del" data-del="${item.id}">删除</span>

        </tr>
        </#list>
        </tbody>

        <tfoot>
        <tr>
            <td colspan="3">
                <div class="total">总计：</div>
            </td>
            <td><span class="v-unit">¥</span><span id="totalPrice" class="value">${total}</span></td>
        </tr>
        </tfoot>

        </#if>
    </table>
    <div class="act-btn" >
        <button class="u-btn u-btn-primary" id="back">退出</button>
<#if cartList?exists && cartList?has_content>
    <button class="u-btn u-btn-primary" id="settle">购买</button>
<#else>
    <button class="u-btn u-btn-primary z-dis" >购买</button>
</#if>

    </div>
</div>


<#include "include/footer.ftl">
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/settleCart.js"></script>
</body>
</html>