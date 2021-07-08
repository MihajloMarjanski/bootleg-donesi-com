Vue.component("myRequests",{

    data: function(){
        return{
            getFor:{
                username: "",
            },
            requests:"",
            selectedReq:{
                reqEntityID:"",
                courier:""
            }
        }
    },
    mounted(){
        this.getFor.username = localStorage.getItem('username')
        axios
        .post('/getMyRequests',this.getFor)
        .then(response=>{
            this.requests = response.data
        })
    },
    template:`
    	<div>
        <h1>Transport Requests</h1>
        <div>
            <table style="width:99.999%">
                <thead>
                    <th style="width:30%">Requested by</th>
                    <th style="width:15%">Order ID</th>
                    <th style="width:30%">Date ordered</th>
                    <th style="width:15%">Price</th>
                    <th style="width:5%"></th>
                    <th style="width:5%"></th>
                </thead>
                <tbody v-for="r in requests">
                    <tr class="nopointerrow" v-for="c in r.requests">
                        <td style="width:30%"> {{c}}</td>
                        <td style="width:15%">{{r.orderID}}</td>
                        <td style="width:30%">{{r.timeOfOrder}}</td>
                        <td style="width:15%">{{r.price}}</td>
                        <td style="width:5%"><button type= "button" v-on:click="approve(r,c)">Approve</button> </td>
                        <td style="width:5%"><button type= "button" v-on:click="deny(r,c)">Deny</button> </td>
                    </tr>                                                                  
                </tbody>
            </table>            
        </div>
        </div>

    `,
    methods:{
        approve(request,courier){
            this.selectedReq.reqEntityID = request.entityID
            this.selectedReq.courier = courier
            axios
            .post('/approveTransport',this.selectedReq)
            .then(response=>{
                axios
                .post('/getMyRequests',this.getFor)
                .then(response=>{
                    this.requests = response.data
                })
            })
            .catch((error) => {
              });

        },
        deny(request,courier){
            this.selectedReq.reqEntityID = request.entityID
            this.selectedReq.courier = courier
            axios
            .post('/denyTransport',this.selectedReq)
            .then(response=>{
                axios
                .post('/getMyRequests',this.getFor)
                .then(response=>{
                    this.requests = response.data
                })
            })
            .catch((error) => {
              });

        }
    }

})