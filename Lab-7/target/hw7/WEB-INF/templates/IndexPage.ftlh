<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <article>
            <template>
                <div class="title"></div>
                <div class="information">By Vovuh, 2 days ago, translation</div>
                <div class="text"></div>
                <ul class="attachment">
                    <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
                    <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
                </ul>
                <div class="footer">
                    <div class="left">
                        <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                        <span class="positive-score">+173</span>
                        <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                    </div>
                    <div class="right">
                        <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                        2 days ago
                        <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                        <a href="#">68</a>
                    </div>
                </div>
            </template>
            <div class="noData">
                No articles.
            </div>
        </article>

    </div>

    <script>
        $(function () {
            const $noData = $(".articles .noData");
            $noData.text("Loading...");
            ownAjax({
                    action: "findAllArticles"
                },
                function (response) {
                    response["articles"].forEach(function (article) {
                        $noData.hide();
                        const $art = $($(".articles template").prop("content")).clone();
                        $art.find(".title").text(article.title);
                        $art.find(".text").text(article.text);
                        $(".articles article").append($art);
                    });
                }
            );
        })
    </script>
</@c.page>
