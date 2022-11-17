<#import "commons.ftlh" as c>

<@c.page>
    <div class="body">
        <form method="post" action="">
            <div class="article form-box">
                <input type="hidden" name="action" value="publish"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title:</label>
                    </div>
                    <div class="value">
                        <input id="title" name="title">
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text:</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text"></textarea>
                    </div>
                </div>
                <div class="error"></div>
                <div class="button-field">
                    <input type="submit" value="Create">
                </div>
            </div>
        </form>
    </div>

    <script>
        $(function () {
            $(".article form").submit(function () {
                const title = $(this).find("input[name='title']").val();
                const text = $(this).find("input[name='text']").val();
                const $error = $(this).find(".error");

                ownAjax({
                    action: publish,
                    title: title,
                    text: text
                }, function (response) {
                    if (response["error"]) {
                        $error.text(response["error"]);
                    } else {
                        location.href = response["redirect"];
                    }
                });
                return false;
            });
        })
    </script>
</@c.page>