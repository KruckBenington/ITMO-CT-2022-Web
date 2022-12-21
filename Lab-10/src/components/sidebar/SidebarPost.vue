<template>
  <section>
    <div class="header">
      {{ post.title }}
    </div>
    <div class="body">
      {{ post.text }}
    </div>
    <div class="footer">
      <a href="#" @click.prevent="redirectToPostView(post)">View all</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "SidebarPost",
  props: ["post"],
    methods: {
        redirectToPostView: function (post) {
            this.$root.$emit("onPostView", post);
        }
    }
}
</script>

<style scoped>
section {
  margin-bottom: 1rem;
}
</style>