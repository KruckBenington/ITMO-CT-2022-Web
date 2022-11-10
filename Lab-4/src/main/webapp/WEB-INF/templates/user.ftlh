<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if handle?has_content>
        <#assign u = c.findBy(users, "handle", handle)!/>
    <#else>
        <#assign u = c.findBy(users, "id", user_id)!/>
    </#if>

    <#if u?has_content>
        <#assign fields = [
        {"name": "id", "value": u.id},
        {"name": "handle", "value": u.handle},
        {"name": "name", "value": u.name}
        ]/>
        <div class="user_bar">
            <div class="user_information">
                <img src="/img/no-title.jpg" title="No title" alt="No title">
                <h2> <@c.userlink u false/> </h2>
                <ul>
                    <#list fields as field>
                        <li>${field.name}: ${field.value}</li>
                    </#list>
                    <li><a href="/posts?user_id=${u.id}">Posts</a>: ${countOfPosts(u)}</li>
                </ul>
            </div>
            <div class="nav_arrows">
                <div class="left_arrow">
                    <#if (0 < users?seq_index_of(u))>
                        <div class="enabled">
                            <@userlinkByContent users[users?seq_index_of(u) - 1] "←"/>
                        </div>
                    <#else>
                        <div class="disabled">
                            ←
                        </div>
                    </#if>
                </div>
                <div class="right_arrow">
                    <#if (users?size - 1 > users?seq_index_of(u))>
                        <div class="enabled">
                            <@userlinkByContent users[users?seq_index_of(u) + 1] "→"/>
                        </div>
                    <#else>
                        <div class="disabled">
                            →
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>

<#macro userlinkByContent user content>
    <a href="/user?handle=${user.handle}">${content}</a>
</#macro>

<#function countOfPosts user>
    <#assign count = 0/>
    <#list posts as post>
        <#if (post.userId == user.id)>
            <#assign count++/>
        </#if>
    </#list>
    <#return count/>
</#function>
