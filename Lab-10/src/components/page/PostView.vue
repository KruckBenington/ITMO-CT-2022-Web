<template>
    <div class="postView">
        <Post :users="users" :comments="comments" :post="post"/>
        <div class="form" @submit.prevent="onWriteComment" v-if="userId">
            <div class="header">Write Comment</div>
            <div class="body">
                <form method="post" action="">
                    <input type="hidden" name="action" value="writeComment"/>
                    <div class="field">
                        <div class="name">
                            <label for="text">Text</label>
                        </div>
                        <div class="value">
                            <textarea id="text" name="text" v-model="text"></textarea>
                        </div>
                    </div>
                    <div class="field error">{{ error }}</div>
                    <div class="button-field">
                        <input type="submit" value="Write">
                    </div>
                </form>
            </div>
        </div>
        <div class="comments" v-for="comment in getPostComments(post.id)" :key="comment.id">
            <div class="comment">
                {{ users[comment.userId].name }} : {{ comment.text }}
            </div>
        </div>
    </div>
</template>

<script>
import Post from "./Post";

export default {
    name: "Index",
    props: ["users", "comments", "post", "userId"],
    components: {
        Post
    },
    data: function () {
        return {
            text: "",
            error: ""
        }
    },
    methods: {
        getPostComments: function (postId) {
            return Object.values(this.comments).filter(comment => comment.postId === postId);
        },
        onWriteComment: function () {
            this.$root.$emit("onWriteComment", this.userId, this.text, this.post.id);
        }
    },
    beforeCreate() {
        this.$root.$on("onWriteCommentValidationError", (error) => this.error = error);
    }
}
</script>

<style scoped>

</style>
