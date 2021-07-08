Vue.component("myRestaurant",{

    data: function(){
        return{
            window:"",
        }
    },
    mounted(){
        this.window = "INFO"
    },
    template:`
    	<div>
            <div>
                <button type= "button" v-on:click="info">Information</button>
                <button type= "button" v-on:click="menu">Menu</button>
                <button type= "button" v-on:click="menuitem">Add Menu Item</button>
                <button type= "button" v-on:click="comments">Comments</button>
                <button type= "button" v-on:click="orders">Orders</button>
                <button type= "button" v-on:click="customers">Customers</button>
                <button type= "button" v-on:click="requests">Transport Requests</button>
            </div>
            <div v-if="window === 'INFO'">
                <myRestaurantInfo></myRestaurantInfo>
            </div>
            <div v-if="window === 'MENU'">
                <myMenu></myMenu>
            </div>
            <div v-if="window === 'ADDMENU'">
                <addMenuItem></addMenuItem>
            </div>
            <div v-if="window === 'COMMENTS'">
                <myComments></myComments>
            </div>
            <div v-if="window === 'ORDERS'">
                <orders></orders>
            </div>
            <div v-if="window === 'CUSTOMERS'">
                <myCustomers></myCustomers>
            </div>
            <div v-if="window === 'REQUESTS'">
                <myRequests></myRequests>
            </div>         
        </div>

    `,
    methods:{
        info(){
            this.window = "INFO"
        },
        menu(){
            this.window = "MENU"    
        },
        menuitem(){
            this.window = "ADDMENU"
        },
        comments(){
            this.window = "COMMENTS"
        },
        orders(){
            this.window = "ORDERS"
        },
        customers(){
            this.window = "CUSTOMERS"
        },
        requests(){
            this.window = "REQUESTS"
        },
    }

})