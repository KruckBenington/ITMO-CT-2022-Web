<#import "./common.ftlh" as c>

<@c.page>
    <div class="datatable">
        <div class="caption">User list</div>
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Handle</th>
                <th>Id</th>
            </tr>
            </thead>
            <tbody>
            <#list users as u>
                <tr>
                    <td>
                        <#if user?? && user.id == u.id>*</#if> <@c.userlink u true/>
                    </td>
                    <td>
                        <@c.userlink u false/>
                    </td>
                    <td>
                        ${u.id}
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
