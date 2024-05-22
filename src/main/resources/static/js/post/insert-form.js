const PostInsertPage = Vue.component('board-insert-form', {
    template: `
            <div class="insert-form">
                <h1>Insert Form</h1>
                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" name="title" v-model="form.title">
                </div>
                <div class="form-group">
                    <label for="content">Content:</label>
                    <textarea name="content" v-model="form.content"></textarea>
                </div>
                <button @click="registerPost">Submit</button>
            </div>
    `,
    data() {
        return {
            form: {
                title: '',
                content: '',
            }
        }
    },
    methods: {
        registerPost() {
            if (this.form.title.length === 0) {
                alert('title cannot be empty.');
                return;
            }
            if (this.form.content.length === 0) {
                alert('content cannot be empty.');
                return;
            }
            fetch('/post', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(this.form),
            }).then((res) => {
                if (res.status !== 201) {
                    if (res.status !== 201) {
                        alert('error');
                        return;
                    }
                }
                return res.json();
            })
                .then((data) => {
                    router.push(`/post/${data}`);
                }).catch((err) => alert(err));
        }
    }
});