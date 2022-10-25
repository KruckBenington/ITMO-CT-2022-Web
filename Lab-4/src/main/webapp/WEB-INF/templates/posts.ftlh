<#-- @ftlvariable name="user_id" type="Long"-->
<#import "./common.ftlh" as c>

<@c.page>
    <#list posts?filter(p -> p.userId == user_id) as post>
        <@c.usePost post true/>
    </#list>
</@c.page>