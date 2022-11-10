<#-- @ftlvariable name="post_id" type="Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#assign p = c.findBy(posts, "id", post_id)!/>

    <#if p?has_content>
        <@c.usePost p true/>
    <#else>
        No such post.
    </#if>
</@c.page>