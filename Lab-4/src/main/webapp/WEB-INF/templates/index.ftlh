<#import "./common.ftlh" as c>

<@c.page>
    <#list posts?reverse as post>
        <@c.usePost post false/>
    </#list>
</@c.page>
