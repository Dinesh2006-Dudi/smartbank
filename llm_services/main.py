from fastapi import FastAPI
import requests
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI(title="Smart Bank LLM Service")
app.add_middleware(CORSMiddleware, allow_origins=["*"], allow_methods=["*"], allow_headers=["*"])

# Transaction Service nundi data tevadam
def get_transactions(user_id, category=None):
    try:
        if category:
            url = f"http://localhost:8083/api/transactions/{user_id}/category/{category}"
        else:
            url = f"http://localhost:8083/api/transactions/{user_id}"
        res = requests.get(url, timeout=5)
        return res.json() if res.status_code == 200 else []
    except:
        return []

def get_accounts(user_id):
    try:
        url = f"http://localhost:8082/api/accounts/user/{user_id}"
        res = requests.get(url, timeout=5)
        return res.json() if res.status_code == 200 else []
    except:
        return []

@app.get("/")
def home():
    return {"message": "LLM Service Running guru!"}

@app.post("/chat")
def chat_bot(data: dict):
    message = data.get("message", "").lower()
    user_id = str(data.get("userId", "1"))

    # 1. Balance check
    if "balance" in message or "account" in message:
        accounts = get_accounts(user_id)
        if not accounts:
            return {"answer": "Nee account kanipinchaledu guru. Mundhu account create chey."}
        total = sum([a.get('balance',0) for a in accounts])
        return {"answer": f"Nee total balance Rs.{total}. {len(accounts)} accounts unnai.", "data": accounts}

    # 2. Food spending
    if "food" in message or "zomato" in message or "swiggy" in message:
        txs = get_transactions(user_id, "food")
        total = sum([t.get('amount',0) for t in txs])
        return {"answer": f"Guru nuvvu food meedha Rs.{total} karchu chesav. Total {len(txs)} orders chesav.", "data": txs}

    # 3. Total spending
    if "total" in message or "entha karchu" in message or "spending" in message:
        txs = get_transactions(user_id)
        total = sum([t.get('amount',0) for t in txs])
        return {"answer": f"Nee total karchu Rs.{total} for {len(txs)} transactions.", "data": txs}

    # 4. Travel
    if "travel" in message or "uber" in message or "ola" in message:
        txs = get_transactions(user_id, "travel")
        total = sum([t.get('amount',0) for t in txs])
        return {"answer": f"Travel ki Rs.{total} ayyindi guru, {len(txs)} trips.", "data": txs}

    return {"answer": "Nenu 'balance entha?', 'food entha karchu?', 'total spending entha?' lanti questions ki answer ista guru. Try chey!"}