const PostListPage = Vue.component('board-list', {
    template: `
            <section>
                <h1>Post List</h1>
                <table>
                    <tr>
                        <th style="width: 20px">no</th>
                        <th style="width: 300px; text-align: left; padding: 10px">title</th>
                        <th style="width: 80px">writer</th>
                        <th style="width: 120px">register date</th>
                    </tr>
                    <tr is="board-item" v-for="(post, index) in postList" :post="post" :key="index"></tr>
                </table>
                <a @click="goWriterPage">write</a>
           </section>
    `,
    data() {
        return {
            postList: [],
        }
    },
    created() {
        fetch('/post')
            .then((res) => res.json())
            .then((data) => this.postList = data);
    },
    methods: {
        goWriterPage() {
            if (vm.loginUser === null) {
                alert('login please..');
                return;
            }
            router.push('/post/new');
        }
    }
});