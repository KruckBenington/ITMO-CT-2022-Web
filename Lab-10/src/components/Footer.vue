<template>
  <footer>
    Users: {{ usersCount }} |
    Posts: {{ postsCount }}
    <a href="/">Codeforces</a> &copy; 2022 by Mike Mirzayanov
  </footer>
</template>

<script>
export default {
  name: "Footer",
  props: ["usersCount", "postsCount"]
}
</script>

<style scoped>

</style>