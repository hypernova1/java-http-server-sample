const BoardList = Vue.component('board-list', {
    template: `
            <section>
                <h1>board-list</h1>
                <table>
                    <tr>
                        <th>no</th>
                        <th>title</th>
                        <th>writer</th>
                        <th>register date</th>
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
            router.push('/board/new');
        }
    }
});