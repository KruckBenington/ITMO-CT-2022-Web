<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index :users="users" :posts="posts" :comments="comments" v-if="page === 'Index'"/>
            <Enter v-if="page === 'Enter'"/>
            <Users :users="users" v-if="page === 'Users'"/>
            <Register v-if="page === 'Register'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <PostView v-if="page === 'PostView'" :users="users" :post="post" :comments="comments" :userId="userId"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./page/Index";
import Enter from "./page/Enter";
import WritePost from "./page/WritePost";
import EditPost from "./page/EditPost";
import Register from "@/components/page/Register";
import Users from "@/components/page/Users";
import PostView from "./page/PostView";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index",
            post: null
        }
    },
    components: {
        Users,
        Register,
        WritePost,
        Enter,
        Index,
        Sidebar,
        EditPost,
        PostView
    },
    props: ["posts", "users", "comments", "userId"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page);
        this.$root.$on("onPostView", (post) => {
            this.page = "PostView";
            this.post = post;
        });
    }
}
</script>

<style scoped>

</style>
