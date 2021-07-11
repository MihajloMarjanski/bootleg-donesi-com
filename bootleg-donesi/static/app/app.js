const Home = { template: '<home></home>' }
const Register = { template: '<register></register>' }
const Login = { template: '<login></login>' }
const RegisterEmployee = { template: '<registerEmployee></registerEmployee>' }
const AllUsers = { template: '<allUsers></allUsers>' }
const AllRestaurants = { template: '<allRestaurants></allRestaurants>' }
const MyAccount = { template: '<myAccount></myAccount>' }
const AddRestaurant = { template: '<addRestaurant></addRestaurant>' }
const RestaurantView = { template: '<restaurantView></restaurantView>' }
const MyRestaurant = { template: '<myRestaurant></myRestaurant>' }
const MyRestaurantInfo = { template: '<myRestaurantInfo></myRestaurantInfo>' }
const MyMenu = { template: '<myMenu></myMenu>' }
const MyComments = { template: '<myComments></myComments>' }
const AddMenuItem = { template: '<addMenuItem></addMenuItem>' }
const Orders = { template: '<orders></orders>' }
const MyRequests = { template: '<myRequests></myRequests>' }
const MyCustomers = { template: '<myCustomers></myCustomers>' }
const Cart = { template: '<cart></cart>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Home},
		{ path: '/register', name: 'register', component: Register},
		{ path: '/login', name: 'login', component: Login},
		{ path: '/registerEmployee', name: 'registerEmployee', component: RegisterEmployee},
		{ path: '/allUsers', name: 'allUsers', component: AllUsers},
		{ path: '/allRestaurants', name: 'allRestaurants', component: AllRestaurants},
		{ path: '/myAccount', name: 'myAccount', component: MyAccount},
		{ path: '/addRestaurant', name: 'addRestaurant', component: AddRestaurant},
		{ path: '/restaurantView', name: 'restaurantView', component: RestaurantView},
		{ path: '/myRestaurant', name: 'myRestaurant', component: MyRestaurant},
		{ path: '/myRestaurantInfo', name: 'myRestaurantInfo', component: MyRestaurantInfo},
		{ path: '/myMenu', name: 'myMenu', component: MyMenu},
		{ path: '/myComments', name: 'myComments', component: MyComments},
		{ path: '/addMenuItem', name: 'addMenuItem', component: AddMenuItem},
		{ path: '/orders', name: 'orders', component: Orders},
		{ path: '/myRequests', name: 'myRequests', component: MyRequests},
		{ path: '/myCustomers', name: 'myCustomers', component: MyCustomers},
		{ path: '/cart', name: 'cart', component: Cart}
	  ]
});

var app = new Vue({
	router,
	el: '#app'
});