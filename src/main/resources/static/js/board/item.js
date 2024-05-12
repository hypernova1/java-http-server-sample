Vue.component('board-item', {
    template: `
            <tr>
                <td>{{ post.id }}</td>
                <td><a @click="viewDetail(post.id)">{{ post.title }}</a></td>
                <td>{{ post.writer }}</td>
                <td>{{ post.createdAt }}</td>
            </tr>
    `,
    props: ['post'],
    methods: {
        viewDetail(id) {
            router.push({ path: 'board/' + id });
        }
    }
});