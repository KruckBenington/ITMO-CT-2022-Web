<template>
        <article>
            <div class="title"><a href="#" @click.prevent="redirectToPostView(post)">{{ post.title }}</a></div>
            <div class="information">By {{ users[post.userId].name }}</div>
            <div class="body">{{ post.text }}</div>
            <div class="footer">
                <div class="left">
                    <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                    <span class="positive-score">+173</span>
                    <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
                </div>
                <div class="right">
                    <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                    <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
                    <a href="#">{{ getCountOfComments(post.id) }}</a>
                </div>
            </div>
        </article>
</template>

<script>
export default {
    name: "Post",
    props: ["post", "comments", "users"],
    methods: {
        getCountOfComments: function (postId) {
            return (Object.values(this.comments).filter(comment => comment.postId === postId)).length;
        },
        redirectToPostView: function (post) {
            this.$root.$emit("onPostView", post);
        }
    }
}
</script>

<style scoped>

</style>
