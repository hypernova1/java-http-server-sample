const BoardList = Vue.component('board-list', {
    template: `
            <section>
                <h1>board-list</h1>
                <ul>
                    <li></li>
                    <li is="board-item" v-for="(post, index) in postList" :post="post" :key="index"></li>
                </ul>
                <router-link to="/board/new">글쓰기</router-link>
           </section>`,
    created() {
        fetch('/board')
            .then((res) => res.json())
            .then((data) => this.postList = data);
    },
    data() {
        return {
            postList: [],
        }
    },
});