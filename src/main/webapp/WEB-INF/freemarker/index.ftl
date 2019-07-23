<!DOCTYPE html>
<html>
<#include "./include/head.ftl">
<body>
<#include "./include/support.ftl">
<#include "./include/header.ftl">
<#if RequestParameters['type']??>
    <#assign listType=RequestParameters['type']?number>
<#else>
    <#assign listType=0>
</#if>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li <#if listType == 0>class="z-sel"</#if> ><a href="/">所有内容</a></li>
            <#if user?? && user.userType == 0>
                <li <#if listType == 1>class="z-sel"</#if> ><a href="/?type=1">未购买的内容</a></li></#if>
            </ul>
        </div>
    </div>
<#if !contentList?exists || !contentList?has_content>
    <div class="n-result">
        <h3>暂无内容！</h3>
    </div>
<#else>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
        <#--如果是买家-->
        <#--未购买的内容-->
            <#if user?? && user.userType == 0 && listType == 1>
                <#list contentList as x>

                    <li id="p-${x.id}">
                        <a href="/show?id=${x.id}" class="link">
                            <div class="img"><img src="${x.imgPath}" alt="${x.title}"></div>
                            <h3>${x.title}</h3>

                            <div class="price"><span class="v-unit">¥</span><span class="v-value">${x.price}</span>
                            </div>
                        </a>
                    </li>

                </#list>
            <#else>
                <#list contentList as x>
                    <li id="p-${x.id}">
                        <a href="/show?id=${x.id}" class="link">
                            <div class="img"><img src="${x.imgPath}" alt="${x.title}"></div>
                            <h3>${x.title}</h3>

                            <div class="price"><span class="v-unit">¥</span><span class="v-value">${x.price}</span>
                            </div>
                        <#--如果是买家且买过该内容-->
                            <#if user?? && user.userType==0 && (x.bePurchased>0)><span class="had"><b>已购买</b></span></#if>
                        <#--如果是卖家且该内容已售出-->
                            <#if user?? && user.userType==1 && (x.bePurchased>0)><span class="had"><b>已售出</b></span></#if>
                        </a>
                    <#--如果是卖家且该内容未售出，可以作删除操作-->
                        <#if user?? && user.userType==1 && x.bePurchased==0><span class="u-btn u-btn-normal u-btn-xs del"
                                                                          data-del="${x.id}">删除</span></#if>
                    <#--如果是卖家但该内容被售出，显示已出售数量-->
                        <#if user?? && user.userType==1 && (x.bePurchased>0)><span class="del">已售出${x.bePurchased}份</span> </#if>
                    </li>
                </#list>
            </#if>
        </ul>
    </div>
</#if>
</div>
<#include "./include/footer.ftl">
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageIndex.js"></script>
</body>
</html>