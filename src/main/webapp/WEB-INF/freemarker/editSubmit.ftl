<!DOCTYPE html>
<html>
<#include "include/head.ftl">
<body>
<#include "include/support.ftl">
<#include "include/header.ftl">
<#assign contentId = RequestParameters['id']>
<div class="g-doc">
    <#if content??>
        <div class="n-result">
            <h3>编辑成功！</h3>
            <p><a href="/show?id=${content.id}">[查看内容]</a><a href="/">[返回首页]</a></p>
        </div>
    <#else>
    <div class="n-result">
        <h3>编辑失败！</h3>
        <p><a href="/edit?id=${contentId}">[重新编辑]</a><a href="/">[返回首页]</a></p>
    </div>
    </#if>
</div>
<#include "include/footer.ftl">
</body>
</html>