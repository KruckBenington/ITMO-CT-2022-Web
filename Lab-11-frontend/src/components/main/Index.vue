<template>
    <div class="articles">
        <Post v-for="post in posts" :post="post" :key="post.id"/>
    </div>
</template>

<script>
import Post from "../page/Post";

export default {
    name: "Index",
    props: ["posts"],
    components: {
        Post
    }
}
</script>

<style scoped>

</style>
