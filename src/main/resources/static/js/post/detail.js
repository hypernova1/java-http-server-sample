const PostDetailPage = Vue.component('board-detail', {
    template:
        `
        <div class="post-detail">
            <h1>{{ postDetail.title }}</h1>
            <section v-html="formattedContent"></section>
            <div class="button-wrapper">
              <button @click="goListPage">Back to List</button>
            </div>
          </div>
    `,
    data() {
        return {
            postDetail: {},
        }
    },
    created() {
        const postId = this.$route.params.id;
        fetch(`/post/${postId}`)
            .then((res) => res.json())
            .then((data) => this.postDetail = data);
    },
    methods: {
        goListPage() {
            router.push('/post');
        }
    },
    computed: {
        formattedContent() {
            return this.postDetail.content ? this.postDetail.content.replace(/\n/g, '<br>') : '';
        }
    }
});