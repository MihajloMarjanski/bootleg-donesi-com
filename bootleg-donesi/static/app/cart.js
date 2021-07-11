Vue.component("cart",{

    data: function(){
        return{
            user:{
                entityID:""
            },
            cart:"",
            forRemoval:{
                entityID:"",
                menuItem:""
            }
        }
    },
    mounted(){
        this.user.entityID = localStorage.getItem("id")
        axios
        .post('/getCart',this.user)
        .then(response=>{
            this.cart = response.data
        })
        .catch((error) => {
          });
    },
    template:`
    	<div>
        	<h1>Shopping cart</h1>
            <div>
                <table style="width:99.999%">
                    <thead>
                        <th style="width:20%">Picture</th>
                        <th style="width:15%">Name</th>
                        <th style="width:40%">Description</th>
                        <th style="width:5%">Price</th>
                        <th style="width:5%"></th>
                    </thead>
                    <tbody>
                        <tr class="nopointerrow" v-for="i in cart.menuItems">
                            <td style="width:20%"> <img :src="i.picturePath" width="75" height="75" ></td>
                            <td style="width:15%">{{i.name}}</td>
                            <td style="width:40%">{{i.description}}</td>
                            <td style="width:5%">{{i.price}}</td>
                            <td style="width:5%"><input min="1" v-on:change="change" style="width:99%" type="number" v-model="i.count" placeholder = "Count"/><button type= "button" v-on:click="remove(i)">Remove</button> </td>
                        </tr>                                                                  
                    </tbody>
                </table>
            </div>
            <div class="total">
            <inline style="float: right" v-if="cart">
                <i>Total (Calculated with your discount):</i> {{cart.price}}
                <button type= "button" v-on:click="checkout">Checkout</button>
            </inline>
            </div>
        </div>
    `,
    methods:{
        change(){
            axios
            .post('/changeCart',this.cart)
            .then(response=>{
                axios
                .post('/getCart',this.user)
                .then(response=>{
                    this.cart = response.data
                })
                .catch((error) => {
                  });
            })
            .catch((error) => {

              });
        },


        remove(menuItem){
            this.forRemoval.entityID = localStorage.getItem("id")
            this.forRemoval.menuItem = menuItem
            axios
            .post('/removeFromCart',this.forRemoval)
            .then(response=>{
                axios
                .post('/getCart',this.user)
                .then(response=>{
                    this.cart = response.data
                })
                .catch((error) => {
                  });
            })
            .catch((error) => {

              });
        },

        checkout(){
            axios
            .post('/checkout',this.cart)
            .then(response=>{
                alert("Added to your orders");
                axios
                .post('/getCart',this.user)
                .then(response=>{
                    this.cart = response.data
                })
                .catch((error) => {
                  });
            })
            .catch((error) => {

              });
        },

    }

})