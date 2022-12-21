<template>
    <div class="articles">
        <Post v-for="post in posts" :users="users" :comments="comments" :post="post" :key="post.id"/>
    </div>
</template>

<script>
import Post from "./Post";

export default {
    name: "Index",
    props: ["posts", "users", "comments"],
    components: {
        Post
    }
}
</script>

<style scoped>

</style>
