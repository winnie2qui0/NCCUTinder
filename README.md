# 💬 NCCU Tinder – 校園社交平台（JavaFX Desktop App）

## 📌 簡介（中文）

**NCCU Tinder** 是一款為政治大學學生設計的桌面社交應用程式，使用 **Java** 搭配 **JavaFX** 製作。  
透過平台，使用者可以註冊、登入、查看主題活動資訊、瀏覽參加者檔案，促進校園內部的社交互動。

此專案主要實踐 **MVC 架構設計**、**JavaFX FXML UI 建構** 以及 **物件導向程式設計原則（OOP）**，也是我在校期間獨立完成的桌面應用作品之一。

---

## 💡 專案亮點
- 🧑‍💻 使用者註冊與登入
- 📅 主題活動建立與報名
- 🪪 個人檔案查看與名單管理
- 📂 採用 MVC 架構與 JavaFX FXML 架 UI
- 💬 預留擴充聊天室與配對功能的結構

---

## 🚀 執行方式

### 系統需求：
- Java 11+
- JavaFX SDK 安裝完成
- 可用 IDE：Eclipse / IntelliJ IDEA

### 執行步驟：
1. 將專案 clone 至本機：
```bash
2.git clone https://github.com/yourusername/nccu-tinder.git
3.開啟 IDE 並設定 JavaFX 環境路徑
4.執行 Main.java 進入程式主畫面

## 📂 專案結構
src/application/
├── Main.java                   # 主程式入口
├── Login.fxml / Controller     # 登入畫面與邏輯
├── Activityitem.fxml           # 活動列表元件
├── Dating.fxml / Controller    # 主配對頁面
├── Showprofile.fxml            # 檢視使用者資訊
├── WaitingList.fxml            # 報名名單管理
├── Profile.java / User.java    # 使用者資料物件
├── Database.java               # 簡易資料模擬儲存
├── application.css             # 全域樣式
