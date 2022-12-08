<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring>


<@c.page>
    <#if user?has_content>
        <ul>
            <li>Id: ${user.id}</li>
            <li>Login: ${user.login}</li>
            <li>CreationTime: ${user.creationTime}</li>
        </ul>
    <#else>
        No such user
    </#if>
</@c.page>

