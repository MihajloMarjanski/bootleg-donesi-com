Vue.component("myComments",{

    data: function(){
        return{
            getFor:{
                username: "",
            },
            comments:""
        }
    },
    mounted(){
        this.getFor.username = localStorage.getItem('username')
        axios
        .post('/getMyComments',this.getFor)
        .then(response=>{
            this.comments = response.data
        })
    },
    template:`
    	<div>
        <h1>Comments</h1>
        <div>
            <table style="width:99.999%">
                <thead>
                    <th style="width:20%">Username</th>
                    <th style="width:60%">Comment</th>
                    <th style="width:5%">Rating</th>
                    <th style="width:10%"></th>
                </thead>
                <tbody>
                    <tr class="nopointerrow" v-for="c in comments">
                        <td style="width:20%"> {{c.username}}</td>
                        <td style="width:60%">{{c.text}}</td>
                        <td style="width:5%">{{c.rating}}</td>
                        <td v-if="c.approved === 'APPROVED'" style="width:5%">APPROVED</td>
                        <td v-if="c.approved === 'WAITING'" style="width:10%"><button type= "button" v-on:click="approve(c)">Approve</button> <button type= "button" v-on:click="deny(c)">Deny</button> </td>
                        <td v-if="c.approved === 'DENIED'" style="width:5%">DENIED</td>
                    </tr>                                                                  
                </tbody>
            </table>            
        </div>
        </div>

    `,
    methods:{
        approve(comment){
            axios
            .post('/approveComment',comment)
            .then(response=>{
                axios
                .post('/getMyComments',this.getFor)
                .then(response=>{
                    this.comments = response.data
                })
            })
        },
        deny(comment){
            axios
            .post('/denyComment',comment)
            .then(response=>{
                axios
                .post('/getMyComments',this.getFor)
                .then(response=>{
                    this.comments = response.data
                })
            })
        },
    }

})