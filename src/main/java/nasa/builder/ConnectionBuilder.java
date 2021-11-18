package nasa.builder;

public class ConnectionBuilder {
	
		private String url;
		private String method;
		private String authHeader;
	
		private ConnectionBuilder (Builder builder) {
			this.url = builder.url;
			this.method = builder.method;
			this.authHeader = builder.authHeader;
		}
		
		
		public String getUrl() {
			return url;
		}

		public String getMethod() {
			return method;
		}

		public String getAuthHeader() {
			return authHeader;
		}
		
		
		
		public static class Builder {
			
			private String url;
			private String method;
			private String authHeader;
			
			
			public Builder (String url, String method) {
				this.url = url;
				this.method = method;
			}
			

			public Builder authHeader (String authHeader) {
				this.authHeader = authHeader;
				return this;
			}
			
			
			public ConnectionBuilder build () {
				
				return new ConnectionBuilder(this);
			}
		}

		
		
		
	

}
