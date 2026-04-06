import { useState, useEffect } from "react";
import axios from "axios";
import { motion } from "framer-motion";
import "./App.css";

function App() {

  const [senderId, setSenderId] = useState("");
  const [receiverId, setReceiverId] = useState("");
  const [amount, setAmount] = useState("");
  const [transactions, setTransactions] = useState([]);
  const [balance, setBalance] = useState(0);

  const API_URL = "http://localhost:8081/api/users";

  // Fetch transactions
  const getTransactions = async () => {
    try {
      if (!senderId) return;
      const res = await axios.get(`${API_URL}/transactions/${senderId}`);
      setTransactions(res.data);
    } catch (err) {
      console.log(err);
    }
  };

  // Fetch balance
  const getBalance = async () => {
    try {
      if (!senderId) return;
      const res = await axios.get(`${API_URL}/${senderId}`);
      setBalance(res.data.balance);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getTransactions();
    getBalance();
  }, [senderId]);

  // Transfer money
  const transferMoney = async () => {
    if (!senderId || !receiverId || !amount) {
      alert("Fill all fields");
      return;
    }

    try {
      await axios.post(`${API_URL}/transfer`, {
        senderId: Number(senderId),
        receiverId: Number(receiverId),
        amount: Number(amount)
      });

      alert("Transfer Successful 💰");
      setAmount("");
      getTransactions();
      getBalance();

    } catch (err) {
      alert("Transfer Failed ❌");
    }
  };

  return (
    <div className="container">

      {/* Header */}
      <motion.h1
        initial={{ opacity: 0, y: -40 }}
        animate={{ opacity: 1, y: 0 }}
      >
        Smart Wallet
      </motion.h1>

      {/* Wallet Card */}
      <motion.div
        className="wallet-card"
        initial={{ scale: 0.8, opacity: 0 }}
        animate={{ scale: 1, opacity: 1 }}
      >
        <h3>Wallet Balance</h3>
        <h1>₹{balance}</h1>
      </motion.div>

      {/* Transfer Card */}
      <motion.div
        className="card"
        initial={{ x: -100, opacity: 0 }}
        animate={{ x: 0, opacity: 1 }}
      >
        <h2>Send Money</h2>

        <input
          placeholder="Sender ID"
          value={senderId}
          onChange={(e) => setSenderId(e.target.value)}
        />

        <input
          placeholder="Receiver ID"
          value={receiverId}
          onChange={(e) => setReceiverId(e.target.value)}
        />

        <input
          placeholder="Amount"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />

        <button onClick={transferMoney}>Send</button>
      </motion.div>

      {/* Transactions */}
      <motion.div
        className="card"
        initial={{ x: 100, opacity: 0 }}
        animate={{ x: 0, opacity: 1 }}
      >
        <h2>Transaction History</h2>

        {transactions.length === 0 ? (
          <p>No transactions</p>
        ) : (
          transactions.map((t) => (
            <motion.div
              key={t.id}
              className="txn"
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
            >
              <p><b>{t.senderId}</b> ➜ <b>{t.receiverId}</b></p>
              <p>₹{t.amount}</p>
              <small>{t.timestamp}</small>
            </motion.div>
          ))
        )}
      </motion.div>

    </div>
  );
}

export default App;