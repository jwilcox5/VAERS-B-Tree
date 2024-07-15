public class BPlusTree 
{
	abstract class Node 
	{
		protected int[] keys;
		protected int keyCount;
		protected Node parentNode;
		protected Node leftSibling;
		protected Node rightSibling;
		
		protected Node() 
		{
			this.keyCount     = 0;
			this.parentNode   = null;
			this.leftSibling  = null;
			this.rightSibling = null;
		}

		public int getKeyCount() 
		{
			return this.keyCount;
		}

		public int getKey(int index) 
		{
			return this.keys[index];
		}

		public void setKey(int index, int key) 
		{
			this.keys[index] = key;
		}

		public Node getParent() 
		{
			return this.parentNode;
		}

		public void setParent(Node parent) 
		{
			this.parentNode = parent;
		}	
		
		public abstract String getNodeType();
		public abstract int search(int key);
		
		public boolean isOverflow() 
		{
			return this.getKeyCount() == this.keys.length;
		}
		
		public Node dealWithOverflow() 
		{
			int midIndex = this.getKeyCount() / 2;
			int midIndexKey = this.getKey(midIndex);
			
			Node newRightNode = this.splitNodes();
					
			if(this.getParent() == null) 
			{
				this.setParent(new InternalNode());
			}

			newRightNode.setParent(this.getParent());
			
			newRightNode.setLeftSibling(this);
			newRightNode.setRightSibling(this.rightSibling);

			if(this.getRightSibling() != null)
			{
				this.getRightSibling().setLeftSibling(newRightNode);
			}
				
			this.setRightSibling(newRightNode);
			
			return this.getParent().pushKey(midIndexKey, this, newRightNode);
		}
		
		public abstract Node splitNodes();
		public abstract Node pushKey(int key, Node leftChild, Node rightNode);
		
		public Node getLeftSibling() 
		{
			if(this.leftSibling != null && this.leftSibling.getParent() == this.getParent())
			{
				return this.leftSibling;
			}
				
			return null;
		}

		public void setLeftSibling(Node sibling) 
		{
			this.leftSibling = sibling;
		}

		public Node getRightSibling() 
		{
			if(this.rightSibling != null && this.rightSibling.getParent() == this.getParent())
			{
				return this.rightSibling;
			}
				
			return null;
		}

		public void setRightSibling(Node sibling) 
		{
			this.rightSibling = sibling;
		}
	}

	class InternalNode extends Node
	{
		private Node[] children; 
		
		public InternalNode() 
		{
			this.keys = new int[degree + 1];
			this.children = new Node[degree + 2];
		}
		
		public Node getChild(int index) 
		{
			return this.children[index];
		}

		public void setChild(int index, Node child) 
		{
			this.children[index] = child;

			if(child != null)
			{
				child.setParent(this);
			}	
		}
		
		@Override
		public String getNodeType() 
		{
			return "InternalNode";
		}
		
		@Override
		public int search(int key) 
		{
			int i = 0;

			for(i = 0; i < this.getKeyCount(); i++)
			{
				if(this.getKey(i) == key) 
				{
					return i + 1;
				}

				else if(this.getKey(i) > key) 
				{
					return i;
				}
			}
			
			return i;
		}
		
		public void insertAt(int index, int key, Node leftChild, Node rightChild) 
		{
			for(int i = this.getKeyCount() + 1; i > index; i--) 
			{
				this.setChild(i, this.getChild(i - 1));
			}

			for(int i = this.getKeyCount(); i > index; i--) 
			{
				this.setKey(i, this.getKey(i - 1));
			}
			
			this.setKey(index, key);

			this.setChild(index, leftChild);

			this.setChild(index + 1, rightChild);

			this.keyCount++;
		}
		
		@Override
		public Node splitNodes() 
		{
			int midIndex = this.getKeyCount() / 2;
			
			InternalNode newRightNode = new InternalNode();

			for(int i = midIndex + 1; i < this.getKeyCount(); i++) 
			{
				newRightNode.setKey(i - midIndex - 1, this.getKey(i));

				this.setKey(i, -1);
			}

			for(int i = midIndex + 1; i <= this.getKeyCount(); i++) 
			{
				newRightNode.setChild(i - midIndex - 1, this.getChild(i));

				newRightNode.getChild(i - midIndex - 1).setParent(newRightNode);

				this.setChild(i, null);
			}

			this.setKey(midIndex, -1);

			newRightNode.keyCount = this.getKeyCount() - midIndex - 1;

			this.keyCount = midIndex;
			
			return newRightNode;
		}
		
		@Override
		public Node pushKey(int key, Node leftChild, Node rightNode) 
		{
			int index = this.search(key);
			
			this.insertAt(index, key, leftChild, rightNode);

			if(this.isOverflow()) 
			{
				return this.dealWithOverflow();
			}

			else 
			{
				return this.getParent() == null ? this : null;
			}
		}
	}

	class LeafNode extends Node
	{
		private Patient[] patients;
		
		public LeafNode() 
		{
			this.keys = new int[degree + 1];
			this.patients = new Patient[degree + 1];
		}

		public Patient getPatient(int index) 
		{
			return this.patients[index];
		}

		public void setPatient(int index, Patient patient) 
		{
			this.patients[index] = patient;
		}
		
		@Override
		public String getNodeType() 
		{
			return "LeafNode";
		}
		
		@Override
		public int search(int key) 
		{
			for(int i = 0; i < this.getKeyCount(); i++) 
			{
				if(this.getKey(i) == key) 
				{
					return i;
				}

				else if(this.getKey(i) > key) 
				{
					return -1;
				}
			}
			
			return -1;
		}
		
		public void insertKey(int key, Patient patient) 
		{
			int i = 0;

			while(i < this.getKeyCount() && this.getKey(i) < key)
			{
				i++;
			}
				
			this.insertAt(i, key, patient);
		}
		
		public void insertAt(int index, int key, Patient patient) 
		{
			for(int i = this.getKeyCount() - 1; i >= index; i--) 
			{
				this.setKey(i + 1, this.getKey(i));
				this.setPatient(i + 1, this.getPatient(i));
			}
			
			this.setKey(index, key);

			this.setPatient(index, patient);

			this.keyCount++;
		}
		
		@Override
		public Node splitNodes() 
		{
			int midIndex = this.getKeyCount() / 2;
			
			LeafNode newRightNode = new LeafNode();

			for(int i = midIndex; i < this.getKeyCount(); i++) 
			{
				newRightNode.setKey(i - midIndex, this.getKey(i));

				newRightNode.setPatient(i - midIndex, this.getPatient(i));

				this.setKey(i, -1);

				this.setPatient(i, null);
			}

			newRightNode.keyCount = this.getKeyCount() - midIndex;
			this.keyCount = midIndex;
			
			return newRightNode;
		}
		
		@Override
		public Node pushKey(int key, Node leftChild, Node rightNode) 
		{
			throw new UnsupportedOperationException();
		}
	}

	private static int degree;
	private Node root;
	
	public BPlusTree() 
	{
		this.root = new LeafNode();
		degree = 0;
	}

	public void setDegree(int newDegree)
	{
		degree = newDegree;
	}

	public void insertPatient(int key, Patient patient) 
	{
		LeafNode leaf = this.findLeafNode(key);

		leaf.insertKey(key, patient);
		
		if(leaf.isOverflow()) 
		{
			Node n = leaf.dealWithOverflow();

			if(n != null)
			{
				this.root = n;
			} 
		}
	}
	
	public Patient searchPatient(int key) 
	{
		LeafNode leaf = this.findLeafNode(key);
		
		int index = leaf.search(key);

		return (index == -1) ? null : leaf.getPatient(index);
	}
	
	public LeafNode findLeafNode(int key) 
	{
		Node node = this.root;

		while(node.getNodeType().equals("InternalNode")) 
		{
			node = ((InternalNode) node).getChild(node.search(key));
		}
		
		return (LeafNode) node;
	}
}