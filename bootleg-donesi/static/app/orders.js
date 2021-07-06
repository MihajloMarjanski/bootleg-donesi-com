Vue.component("orders",{

    data: function(){
        return{
            user:{
                entityID:"",
                role:"",
            },
            orders:"",
            searchParams:{
                entityID:"",
                role:"",
                restaurantName:"",
                priceFrom:"",
                priceTo:"",
                dateFrom:"",
                dateTo:"",
                restaurantType:"",
                ordreStatus:"",
                sort:""
            },



        }
    },
    mounted(){
        this.user.entityID = localStorage.getItem('id')
        this.user.role = localStorage.getItem('role')
        this.searchParams.entityID = this.user.entityID
        this.searchParams.role = this.user.role
        axios
        .post('/getOrders',this.user)
        .then(response=>{
            this.orders = response.data
        })
        .catch((error) => {
          });


    },
    template:`
    <div>
    <h1>Orders</h1>
    <div>
        <input v-if="user.role === 'CUSTOMER' || user.role === 'COURIER'" type="text" v-model="searchParams.restaurantName" placeholder="Restaurant Name"/>
        <input type="number" v-model="searchParams.priceFrom" placeholder="Price From"/>
        <input type="number" v-model="searchParams.priceTo" placeholder="Price To"/>
        <label for="dateFrom"><b>Date from</b></label>
        <input type="date" name="dateFrom" v-model="searchParams.dateFrom"/>
        <label for="dateTo"><b>Date to</b></label>
        <input type="date" name="dateTo" v-model="searchParams.dateTo"/>
        </br>
        <label for="status"><b>Order status</b></label>
        <select name="status" v-model="searchParams.ordreStatus" id="status">
            <option value="PROCESSING">Processing</option>
            <option value="INPREP">In preparation</option>
            <option value="WAITING">Waiting for transport</option>
            <option value="TRANSPORT">In transport</option>
            <option value="DELIVERED">Delivered</option>
            <option value="CANCELED">Canceled</option>
        </select>
        <label for="type"><b>Restaurant type</b></label>
        <select v-if="user.role === 'CUSTOMER' || user.role === 'COURIER'" name="type" v-model="searchParams.restaurantType" id="type">
            <option value="ITALIAN">Italian</option>
            <option value="CHINESE">Chinese</option>
            <option value="GRILL">Grill</option>
            <option value="GREEK">Greek</option>
            <option value="MEXICAN">Mexican</option>
        </select>
        <label for="sort"><b>Sort by</b></label>
        <select name="sort" v-model="searchParams.sort" id="sort">
            <option v-if="user.role === 'CUSTOMER' || user.role === 'COURIER'" value="ASCNAME">Restaurant Name Ascending</option>
            <option v-if="user.role === 'CUSTOMER' || user.role === 'COURIER'" value="DESCNAME">Restaurant Name Descending</option>
            <option value="ASCPRICE">Price Ascending</option>
            <option value="DESCPRICE">Price Descending</option>
            <option value="ASCDATE">Date Ordered Ascending</option>
            <option value="DESCDATE">Date Ordered Descending</option>
        </select>
        <button type="button" v-on:click="search">Filter</button>
    </div>
    <table style="width:99.999%">
        <thead>
            <th v-if="user.role === 'CUSTOMER' || user.role === 'COURIER'" style="width:20%">Restaurant</th>
            <th style="width:20%">Price</th>
            <th style="width:20%">Date</th>
            <th style="width:20%">Status</th>
            <th style="width:5%"></th>
        </thead>
        <tbody>
            <tr class="nopointerrow" v-for="o in orders" style="height:40px">
                <td v-if="user.role === 'CUSTOMER' || user.role === 'COURIER'" style="width:20%">{{o.restaurantName}}</td>
                <td style="width:20%">{{o.price}}</td>
                <td style="width:20%">{{o.timeOfOrder}}</td>
                <td v-if="o.orderStatus === 'INPREP'" style="width:20%">IN PREPARATION</td>
                <td v-if="o.orderStatus === 'WAITING'" style="width:20%">WAITING FOR TRANSPORT</td>
                <td v-if="o.orderStatus === 'TRANSPORT'" style="width:20%">IN TRANSPORT</td>
                <td v-if="o.orderStatus !== 'TRANSPORT' && o.orderStatus !== 'WAITING' && o.orderStatus !== 'INPREP'" style="width:20%">{{o.orderStatus}}</td>
            </tr>
        </tbody>
    </table>



    </div>

    `,
    methods:{
        search(){
            axios
            .post('/searchOrders', this.searchParams)
            .then(response=>{
                this.orders = response.data
            })
            .catch((error) => {
              });
        }
    }

})