<!DOCTYPE html>
<html>
<#include "include/head.ftl">
<body>
<#include "include/support.ftl">
<#include "include/header.ftl">
<div class="g-doc">
<#if !content?exists>
    <div class="n-result">
        <h3>内容不存在！</h3>
    </div>
<#else>
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="${content.imgPath}" alt=""></div>
        <div class="cnt">
            <h2>${content.title}</h2>

            <p class="summary">${content.cAbstract}</p>

            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${content.price}</span>
            </div>
        <#--当前是买家-->
            <#if user?? && user.userType==0>
                <div class="num">
                    购买数量：<span class="lessNum" id="minusNum"><a>-</a></span>
                    <span class="totalNum" id="allNum">1</span>
                    <span class="moreNum" id="plusNum"><a>+</a></span>
                </div>
            </#if>

            <div class="oprt f-cb">
            <#--当前是买家-->
                <#if user?? && user.userType==0>
                    <#if (content.bePurchased>0)>
                        <span class="u-btn u-btn-primary z-dis">已购买</span>
                        <span class="buyprice">当时购买价格：¥${content.bePurchased}</span>
                    <#else>
                        <button class="u-btn u-btn-primary" id="addToCart" data-buy="${content.id}">加入购物车</button>
                    </#if>
                </#if>
            <#--当前是卖家-->
                <#if user?? && user.userType==1>
                    <a href="/edit?id=${content.id}" class="u-btn u-btn-primary">编 辑</a>
                </#if>
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${content.text}
    </div>
</#if>
</div>
<#include "include/footer.ftl">
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageShow.js"></script>
</body>
</html>