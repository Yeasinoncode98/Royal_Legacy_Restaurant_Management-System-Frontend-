# 🍽️ Royal Legacy — Fine Dining

> A modern, responsive restaurant ordering website built with **HTML5, CSS3, and Vanilla JavaScript**, connected to a Spring Boot REST API.

[![Live Demo](https://img.shields.io/badge/demo-live-brightgreen?style=for-the-badge)](https://royal-legacy-restaurant-management.vercel.app/)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Vercel](https://img.shields.io/badge/Vercel-000000?style=for-the-badge&logo=vercel&logoColor=white)

**🔗 Live Site:** **[royal-legacy-restaurant-management.vercel.app](https://royal-legacy-restaurant-management.vercel.app/)**
**🔧 Backend Repo:** [Restaurant-Management-System — Backend](#)

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Configuration](#-configuration)
- [Running Locally](#-running-locally)
- [Pages](#-pages)
- [API Integration](#-api-integration)
- [Deployment](#-deployment)
- [Troubleshooting](#-troubleshooting)
- [Author](#-author)

---

## 📖 Overview

**Royal Legacy** is a fine-dining restaurant ordering platform built as a university Java OOP final project. Customers can explore the menu, build a cart, and place orders in real time. The frontend is a pure static site — no frameworks, no build step — talking to a Spring Boot + H2 backend over REST.

---

## ✨ Features

| | |
|---|---|
| 🍔 | Browse foods by category, with live search |
| 📄 | Detailed food view — image, price, rating, description |
| 🛒 | Add / remove cart items, adjust quantity in real time |
| 💰 | Automatic bill calculation |
| 🔐 | User register / login / logout |
| 📦 | Checkout with delivery details |
| ✅ | Order success page with order number & delivery estimate |
| 🛠️ | Admin panel — login, add / edit / delete food |
| 📱 | Fully responsive — desktop, tablet, mobile |
| 🎨 | Smooth animations — fade-in, slide-up, hover, image zoom, back-to-top |

---

## 🛠 Tech Stack

| Layer | Technology |
|-------|-----------|
| Markup | HTML5 |
| Styling | CSS3 — Flexbox / Grid, light glassmorphism, custom design system |
| Logic | Vanilla JavaScript (ES6+) |
| HTTP | Fetch API |
| Hosting | Vercel |

No build tools, frameworks, or bundlers — pure static site, deployed as-is.

---

## 📁 Project Structure

```
frontend/
├── index.html                # Home page
├── menu.html                 # Menu / food listing
├── food-details.html         # Single food details
├── cart.html                 # Cart page
├── checkout.html              # Checkout page
├── login.html
├── register.html
├── about.html
├── contact.html
├── order-success.html
├── admin/
│   ├── login.html
│   └── dashboard.html
├── css/
│   ├── style.css
│   └── responsive.css
├── js/
│   ├── config.js              # Backend URL config
│   ├── api.js                  # Fetch wrapper functions
│   ├── auth.js                  # Login / Register / Logout logic
│   ├── menu.js                   # Menu rendering + search
│   ├── cart.js                    # Cart state (localStorage) + totals
│   ├── checkout.js
│   └── admin.js
├── assets/
│   └── images/
└── README.md
```

---

## ⚙️ Configuration

Backend URL lives in `js/config.js`. Since this is a static site with no build step, it switches automatically based on hostname:

```js
const CONFIG = {
  BACKEND_URL: window.location.hostname === "localhost"
    ? "http://localhost:8080"
    : "https://your-backend.onrender.com"
};
```

> ⚠️ Set the production value to your actual Render backend URL before deploying — this is the #1 source of broken food-loading on the live site.

---

## 🚀 Running Locally

1. Clone the repo
```bash
git clone https://github.com/Yeasinoncode98/Restaurant-Management-System.git
cd Restaurant-Management-System/frontend
```

2. Make sure the backend is running locally on `http://localhost:8080` (see backend README).

3. Serve the frontend with any static server:
```bash
npx serve .
# or use the VS Code Live Server extension
```

4. Open in your browser — `http://localhost:5500` (or whichever port your server uses).

---

## 🗺️ Pages

| Page | Description |
|------|--------------|
| Home | Hero section, featured & popular foods, customer reviews |
| Menu | All food items, searchable and filterable |
| Food Details | Single item view |
| Cart | Manage items, live total |
| Checkout | Delivery info, place order |
| Login / Register | User authentication |
| About / Contact | Static info pages |
| Order Success | Confirmation with order number & delivery estimate |
| Admin Dashboard | Add / edit / delete food (admin only) |

---

## 🌐 API Integration

All requests flow through `js/api.js`, using `CONFIG.BACKEND_URL` as the base:

```js
async function getFeaturedFoods() {
  const res = await fetch(`${CONFIG.BACKEND_URL}/api/foods/featured`);
  if (!res.ok) throw new Error("Failed to fetch featured foods");
  return res.json();
}
```

Key endpoints consumed:

| Method | Endpoint | Purpose |
|--------|----------|---------|
| GET | `/api/foods` | List all foods |
| GET | `/api/foods/featured` | Featured foods for home page |
| GET | `/api/foods/{id}` | Food details |
| GET | `/api/foods/search?q=` | Search foods |
| POST | `/api/auth/register` | Register user |
| POST | `/api/auth/login` | Login user |
| POST | `/api/orders` | Place order |
| POST | `/api/admin/foods` | Add food (admin) |
| PUT | `/api/admin/foods/{id}` | Edit food (admin) |
| DELETE | `/api/admin/foods/{id}` | Delete food (admin) |

---

## 🚢 Deployment

**Vercel (current live host)**

1. Push code to GitHub
2. Vercel Dashboard → Add New Project → import the repo
3. Root directory: `frontend`
4. Framework preset: **Other** (static site, no build step)
5. Deploy

Since there's no build step, environment variables aren't injected automatically — the `localhost` vs production switch is handled directly in `config.js` (see [Configuration](#-configuration)).

---

## 🐛 Troubleshooting

**`ERR_CONNECTION_REFUSED` on `localhost:8080` after deploy**
`config.js` is still pointing at localhost in production. Confirm the hostname check in `CONFIG.BACKEND_URL` and that the production value is your real Render URL.

**CORS error in console**
Backend must explicitly allow the live frontend origin in its CORS config (see backend README).

**Blank food list, no console errors**
Backend likely asleep — Render free tier spins down when idle. First request after inactivity can take 30–50s to respond.

---

## 👤 Author

**Yeasin Arafat**
Lead Developer — Royal Legacy
[GitHub](https://github.com/Yeasinoncode98)