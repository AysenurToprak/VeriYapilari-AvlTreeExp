class AVLNode {
    Gorusme data;
    AVLNode left, right;
    int height;

    public AVLNode(Gorusme data) {
        this.data = data;
        this.height = 1;
    }
}

public class AVLTree {
    AVLNode root;

    // AVL ağacına düğüm ekleme işlemi
    public AVLNode insert(AVLNode node, Gorusme data) {
        if (node == null) return new AVLNode(data);

        if (data.gorusmeID < node.data.gorusmeID) {
            node.left = insert(node.left, data);
        } else if (data.gorusmeID > node.data.gorusmeID) {
            node.right = insert(node.right, data);
        } else {
            return node; // Duplicate değerler kabul edilmez
        }

        // Yükseklik güncellenir
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Denge durumu kontrol edilir ve dengesizlik varsa rotasyonlar yapılır
        int balance = getBalance(node);

        // Sol-Sol Durumu
        if (balance > 1 && data.gorusmeID < node.left.data.gorusmeID) {
            return rightRotate(node);
        }
        // Sağ-Sağ Durumu
        if (balance < -1 && data.gorusmeID > node.right.data.gorusmeID) {
            return leftRotate(node);
        }
        // Sol-Sağ Durumu
        if (balance > 1 && data.gorusmeID > node.left.data.gorusmeID) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Sağ-Sol Durumu
        if (balance < -1 && data.gorusmeID < node.right.data.gorusmeID) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Yükseklik alma
    public int height(AVLNode node) {
        if (node == null) return 0;
        return node.height;
    }

    // Dengesizlik kontrolü
    public int getBalance(AVLNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    // Sağa dönme
    public AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Dönüş
        x.right = y;
        y.left = T2;

        // Yüksekliklerin güncellenmesi
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Sola dönme
    public AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Dönüş
        y.left = x;
        x.right = T2;

        // Yüksekliklerin güncellenmesi
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Dengeleme ve düğüm ekleme
    public void insert(Gorusme data) {
        root = insert(root, data);
    }

    // Sıralı bir şekilde AVL ağacını dolaşarak verileri ekrana yazdırma
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(AVLNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println("Görüşme ID: " + node.data.gorusmeID + ", Müşteri ID: " + node.data.musteriID + ", Görüşme Süresi: " + node.data.gorBaslama);
            inOrderTraversal(node.right);
        }
    }
}
