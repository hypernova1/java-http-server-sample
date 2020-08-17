const BoardDetail = Vue.component('board-detail', {
    template:
        `<div><h1>{{ postDetail.title }}</h1>
             <section>
                {{ postDetail.content }}
             </section>
            </div>
            `,
    data() {
        return {
            postDetail: {},
        }
    },
    created() {
        const postId = this.$route.params.id;
        fetch(`/board/${postId}`)
            .then((res) => res.json())
            .then((data) => this.postDetail = data);
    }
});