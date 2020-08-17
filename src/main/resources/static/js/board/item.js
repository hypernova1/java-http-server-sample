Vue.component('board-item', {
    template: `
            <li>
                <span>{{ post.id }}</span><a @click="viewDetail(post.id)">{{ post.title }}</a><span>{{ post.content }}</span>
            </li>`
    ,
    props: ['post'],
    methods: {
        viewDetail(id) {
            router.push({ path: 'board/' + id });
        }
    }
});