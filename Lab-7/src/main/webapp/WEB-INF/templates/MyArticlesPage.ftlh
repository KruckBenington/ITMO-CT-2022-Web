<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles datatable">
        <div class="caption">Article</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hidden</th>
            </tr>
            </thead>
            <tbody>
            <#list articles as article>
                <tr>
                    <td class="article-id">${article.id}</td>
                    <td class="article-title">${article.title}</td>
                    <td class="hidden">
                        <div class="button-field">
                            <input type="button" value="
                            <#if article.hidden>
                                Show
                            <#else>
                                Hide
                            </#if>">
                        </div>
                    </td>
                </tr>

            </#list>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            const $noData = $(".articles .noData");
            ownAjax({
                    action: "action"
                },
                function (response) {
                    response["articles"].forEach(function (article) {
                        $noData.hide();
                        const $button = $($(".articles tbody tr td").prop("content")).clone();
                        $button.find("button-field").attr("data-articleId", article["id"]);
                        alert(article.id);
                    });
                    $(".articles tbody input").click(function () {
                        ownAjax({
                                action: "changeHidden",
                                articleId: $(this).attr("data-articleId")
                            },
                            function (response) {
                                /*if ($(this).hidden) {
                                    $(this).val("Show");
                                } else {
                                    $(this).val("Hide");
                                }
                                notify("The hidden was changed");*/
                            });
                    });
                }
            );
        })
    </script>
</@c.page>
